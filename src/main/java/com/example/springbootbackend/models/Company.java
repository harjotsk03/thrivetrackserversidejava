package com.example.springbootbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@Document(collection = "companies")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Company {
    @Id
    private String id;
    private Integer employeeCount;
    private String name;
    private String industry;
    private String dateFounded;
    @Field(targetType = FieldType.STRING)
    private String websiteURL;
    @Field(targetType = FieldType.STRING)
    private String location;
    private String email;
    @Field(targetType = FieldType.STRING)
    private String phoneNumber;
    private Date createdAt;

    private String profilePhoto = "https://studyspotr.s3.us-east-2.amazonaws.com/defaultProfile.jpg";
    public Company() {}

    public Company(String name) {
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getProfilePhoto() {
        return this.profilePhoto;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public String getIndustry() {
        return industry;
    }

    public String getDateFounded() {
        return dateFounded;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setDateFounded(String dateFounded) {
        this.dateFounded = dateFounded;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}