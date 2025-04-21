package com.example.springbootbackend.controllers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootbackend.models.Survey;
import com.example.springbootbackend.models.User;
import com.example.springbootbackend.repositories.SurveysRepository;
import com.example.springbootbackend.repositories.UserRepository;
import com.example.springbootbackend.services.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveysRepository surveyRepository;
    @Autowired
    private SurveyService surveyService;

    @PostMapping("/createSurveyInitial")
    public ResponseEntity<?> createInitialSurveySkeleton(@RequestBody String userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating survey skeleton, missing User ID!");
        }

        Optional<User> userExists = userRepository.findById(userId);
        System.out.println(userId);
        if (!userExists.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "This user does not exist, or the User ID is incorrect!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Survey survey = new Survey();

        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime distantFuture = LocalDateTime.of(9999, Month.DECEMBER, 31, 23, 59);
        survey.setCreatedById(userId);
        survey.setSurveyLastSavedTime(createdDate);
        survey.setSurveyAllowMultipleResponses(false);
        survey.setSurveyDescription("Enter a description about your survey to let those taking it know what its about!");
        survey.setSurveyExpirationTime(distantFuture);
        survey.setSurveyLink("https://thrivetrackclientside.vercel.app/application/surveys/takeSurvey/" + survey.getSurveyId());
        survey.setSurveyScheduledSendTime(distantFuture);
        survey.setSurveyStatus("Draft");
        ArrayList<String> tagsCurrent = new ArrayList<>();
        ArrayList<String> teamsCurrent = new ArrayList<>();
        tagsCurrent.add("");
        teamsCurrent.add("");
        survey.setSurveyTags(tagsCurrent);
        survey.setSurveyTeamsSentTo(teamsCurrent);
        survey.setSurveyTitle("Untitled Survey");
        survey.addSurveyQuestion("");
        survey.addSurveyResponse("");
        surveyService.save(survey);

        survey.removeSurveyQuestion("");
        survey.removeSurveyResponse("");
        surveyService.save(survey);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Survey created successfully!");
        response.put("surveyID", survey.getSurveyId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<?> getSurveyByID(@PathVariable String surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        System.out.println(surveyId);

        if (survey.isPresent()) {
            return ResponseEntity.ok(survey);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getSurveysForUser")
    public ResponseEntity<?> getSurveysForUser(@RequestParam String userId) {
        List<Survey> surveys = surveyRepository.findBySurveyCreatedByUserId(userId);

        if (surveys.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(surveys);
    }

}
