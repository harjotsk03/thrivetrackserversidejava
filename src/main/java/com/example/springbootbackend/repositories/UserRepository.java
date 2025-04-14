package com.example.springbootbackend.repositories;

import com.example.springbootbackend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByCompanyId(String companyId);
}