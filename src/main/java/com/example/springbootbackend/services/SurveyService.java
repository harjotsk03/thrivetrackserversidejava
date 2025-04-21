package com.example.springbootbackend.services;

import com.example.springbootbackend.models.Survey;
import com.example.springbootbackend.repositories.SurveysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyService {

    private final SurveysRepository surveyRepository;

    @Autowired
    public SurveyService(SurveysRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Optional<Survey> findById(String id) {
        return surveyRepository.findById(id);
    }

    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }
}