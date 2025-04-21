package com.example.springbootbackend.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Question {
    @Id
    private String id;

    private String surveyId;
    private String question;
    private ArrayList<ResponseOption> options;

    public Question() {}

    public Question(String stringId, String question) {
        this.surveyId = stringId;
        this.question = question;
        this.options = new ArrayList<>();
    }

    // Getters
    public String getQuestionSurveyId() { return this.surveyId; }
    public String getQuestion() { return this.question; }
    public ArrayList<ResponseOption> getQuestionResponseOptions() { return this.options; }

    // Setters
    public void setQuestion(String newQuestion) { this.question = newQuestion; }
    public void addResponseOption(ResponseOption newOption) { this.options.add(newOption); }
    public void removeResponseOption(ResponseOption deleteOption) { this.options.remove(deleteOption); }
}
