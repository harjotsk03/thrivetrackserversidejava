package com.example.springbootbackend.services;

import com.example.springbootbackend.models.Company;
import com.example.springbootbackend.models.User;
import com.example.springbootbackend.repositories.CompanyRepository;
import com.example.springbootbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    public List<User> getCompanyEmployees(String companyId) {
        return userRepository.findByCompanyId(companyId);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}