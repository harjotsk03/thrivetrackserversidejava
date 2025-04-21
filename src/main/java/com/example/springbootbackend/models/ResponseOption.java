package com.example.springbootbackend.models;

import org.springframework.data.annotation.Id;

public class ResponseOption {
    @Id
    private String id;

    private String responseText;
    private String questionId;

    public ResponseOption () {}

    public ResponseOption (String questionId, String responseText) {
        this.questionId = questionId;
        this.responseText = responseText;
    }

    // Getters
    public String getResponseQuestionId() { return this.questionId; }
    public String getResponseText() { return this.responseText; }

    // Setters
    public void setResponseText(String newResponseText) { this.responseText = newResponseText; }
}
