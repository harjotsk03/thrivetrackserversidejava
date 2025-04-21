package com.example.springbootbackend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Survey {
    @Id
    private String id;

    private String surveyCreatedByUserId;
    private String surveyTitle;
    private String surveyDescription;
    private ArrayList<String> tags;
    private ArrayList<String> teamsSendingTo;
    private LocalDateTime lastedSavedTime;
    private LocalDateTime scheduledSendTime;
    private LocalDateTime expirationTime;
    private Boolean allowMultipleResponses;
    private String status;
    private String surveyLink;
    private ArrayList<String> questionsArr;
    private ArrayList<String> responsesArr;

    public Survey() {
        this.questionsArr = new ArrayList<>();
        this.responsesArr = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.teamsSendingTo = new ArrayList<>();
    }

    public Survey(String surveyCreatedByUserId, String surveyTitle, String surveyDescription, ArrayList<String> tags, ArrayList<String> teamsSendingTo, Boolean allowMultipleResponses, String status, String surveyLink) {
        this.surveyCreatedByUserId = surveyCreatedByUserId;
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.tags = tags;
        this.teamsSendingTo = teamsSendingTo;
        this.allowMultipleResponses = allowMultipleResponses;
        this.status = status;
        this.surveyLink = surveyLink;
        this.questionsArr = new ArrayList<>();
        this.responsesArr = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.teamsSendingTo = new ArrayList<>();
    }

    // Getters
    public String getSurveyCreatedByUserId() { return this.surveyCreatedByUserId; }
    public String getSurveyTitle() { return this.surveyTitle; }
    public String getSurveyDescription() { return this.surveyDescription; }
    public ArrayList<String> getSurveyTags() { return this.tags; }
    public ArrayList<String> getSurveyTeamsSentTo() { return this.teamsSendingTo; }
    public LocalDateTime getSurveyLastSavedTime() { return this.lastedSavedTime; }
    public LocalDateTime getSurveyScheduledSendTime() { return this.scheduledSendTime; }
    public LocalDateTime getSurveyExpirationTime() { return this.expirationTime; }
    public Boolean getSurveyAllowMultipleResponses() { return this.allowMultipleResponses; }
    public String getSurveyStatus() { return this.status; }
    public String getSurveyLink() { return this.surveyLink; }
    public String getSurveyId() { return this.id; }
    public ArrayList<String> getSurveyQuestionsIds() { return this.questionsArr; }
    public ArrayList<String> getSurveyResponsesIds() { return this.responsesArr; }

    // Setters
    public void setSurveyTitle(String newTitle) { this.surveyTitle = newTitle; }
    public void setSurveyDescription(String newDescription) { this.surveyDescription = newDescription; }
    public void setSurveyTags(ArrayList<String> newTags) { this.tags = newTags; }
    public void setSurveyTeamsSentTo(ArrayList<String> newTeamsSentTo) { this.teamsSendingTo = newTeamsSentTo; }
    public void setSurveyLastSavedTime(LocalDateTime newTime) { this.lastedSavedTime = newTime; }
    public void setSurveyScheduledSendTime(LocalDateTime newTime) { this.scheduledSendTime = newTime; }
    public void setSurveyExpirationTime(LocalDateTime newTime) { this.expirationTime = newTime; }
    public void setSurveyAllowMultipleResponses(Boolean allowMultiple) { this.allowMultipleResponses = allowMultiple; }
    public void setSurveyStatus(String newStatus) { this.status = newStatus; }
    public void setSurveyLink(String newLink) { this.surveyLink = newLink; }
    public void addSurveyQuestion(String surveyQuestionId) { this.questionsArr.add(surveyQuestionId); }
    public void removeSurveyQuestion(String surveyQuestionId) { this.questionsArr.remove(surveyQuestionId); }
    public void addSurveyResponse(String surveyResponseId) { this.responsesArr.add(surveyResponseId); }
    public void removeSurveyResponse(String surveyResponseId) { this.responsesArr.remove(surveyResponseId); }
    public void setCreatedById(String userID) { this.surveyCreatedByUserId = userID; }
}
