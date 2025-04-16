package com.example.springbootbackend.repositories;

import com.example.springbootbackend.models.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByEmail(String email);
    Optional<Company> findByName(String name);
    List<Company> findByNameContainingIgnoreCase(String name);
}