package com.example.springbootbackend.controllers;

import com.example.springbootbackend.models.Company;
import com.example.springbootbackend.models.User;
import com.example.springbootbackend.repositories.CompanyRepository;
import com.example.springbootbackend.repositories.UserRepository;
import com.example.springbootbackend.security.JwtUtil;
import com.example.springbootbackend.security.UserDTO;
import com.example.springbootbackend.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required!");
        }
        if (user.getJobTitle() == null || user.getJobTitle().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Job Title is required!");
        }
        if (user.getTeam() == null || user.getTeam().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team is required!");
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is required!");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is required!");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is required!");
        }
        if (user.getCompanyId() == null || user.getCompanyId().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company ID is required!");
        }
        if (user.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role is required!");
        }

        Optional<User> existingUserViaEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserViaEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered!");
        }
        Optional<User> existingUserViaUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserViaUsername.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date timeDate = new Date();
        user.setCreatedAt(timeDate);
        userRepository.save(user);
        Optional<Company> optionalCompany = companyRepository.findById(user.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company not found!");
        }
        Company employeeCompany = optionalCompany.get();
        employeeCompany.setEmployeeCount(employeeCompany.getEmployeeCount() + 1);
        companyService.save(employeeCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully!");
    }


    @GetMapping("/login")
    public ResponseEntity<?>  loginUser(@RequestBody User loginRequest){
        Optional<User> existingUser = userRepository.findByEmail(loginRequest.getEmail());

        if (existingUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = existingUser.get();

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok().body(Map.of("token", token));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        String email = jwtUtil.extractEmail(token);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User userGet = user.get();

        UserDTO userDTO = new UserDTO(userGet);

        return ResponseEntity.ok(userDTO);
    }


}
