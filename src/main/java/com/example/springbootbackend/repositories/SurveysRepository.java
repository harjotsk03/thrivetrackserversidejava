package com.example.springbootbackend.repositories;

import com.example.springbootbackend.models.Survey;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveysRepository extends MongoRepository<Survey, String> {

    Optional<Survey> findByTags(String tag);
    Optional<Survey> findBysurveyTitle(String surveyTitle);
    List<Survey> findBySurveyCreatedByUserId(String userId);
}