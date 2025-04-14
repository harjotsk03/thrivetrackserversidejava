package com.example.springbootbackend.controllers;

import com.example.springbootbackend.models.Company;
import com.example.springbootbackend.models.User;
import com.example.springbootbackend.repositories.CompanyRepository;
import com.example.springbootbackend.security.UserDTO;
import com.example.springbootbackend.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<UserDTO>> getCompanyEmployees(@PathVariable String id) {
        List<User> employees = companyService.getCompanyEmployees(id);
        List<UserDTO> employeesSafe = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++){
            UserDTO userDTO = new UserDTO(employees.get(i));
            employeesSafe.add(userDTO);
        }
        return ResponseEntity.ok(employeesSafe);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        Optional<Company> existingCompanyViaEmail = companyRepository.findByEmail(company.getEmail());
        if (existingCompanyViaEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered to a company or this company already exists registered!");
        }
        Optional<Company> existingCompanyViaName = companyRepository.findByName(company.getName());
        if (existingCompanyViaName.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already registered to a company or this company already exists registered!");
        }
        if (company.getName() == null || company.getName().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company name is required!");
        }
        if (company.getEmail() == null || company.getEmail().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company email is required!");
        }
        if (company.getIndustry() == null || company.getIndustry().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company industry is required!");
        }
        if (company.getDateFounded() == null || company.getDateFounded().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company founded date is required!");
        }
        if (company.getWebsiteURL() == null || company.getWebsiteURL().isBlank()) {
            System.out.println("Company website url is empty");
            company.setWebsiteURL("None");
        }
        if (company.getLocation() == null || company.getLocation().isBlank()) {
            System.out.println("Company location is empty");
            company.setLocation("None");
        }
        if (company.getPhoneNumber() == null || company.getPhoneNumber().isBlank()) {
            System.out.println("Company phone number is empty");
            company.setPhoneNumber("None");
        }

        Date createdDate = new Date();
        company.setCreatedAt(createdDate);
        company.setEmployeeCount(0);
        companyService.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully!");
    }
}