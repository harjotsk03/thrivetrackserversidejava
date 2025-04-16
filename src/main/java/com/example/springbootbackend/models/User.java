package com.example.springbootbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String name;
    private String companyId;
    private Role role;
    private String team;
    private String jobTitle;
    private Date createdAt;
    private String companyName;

    private String profilePhoto = "https://studyspotr.s3.us-east-2.amazonaws.com/defaultProfile.jpg";

    public User() {}

    public User( String email, String password, String name, Role role, String jobTitle, String team, String companyId, String companyName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.jobTitle = jobTitle;
        this.companyId = companyId;
        this.team = team;
        this.companyName = companyName;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role newRole){
        this.role = newRole;
    }

    public String getTeam(){
        return this.team;
    }

    public void setTeam(String newTeam){
        this.team = newTeam;
    }
    public String getJobTitle(){
        return this.jobTitle;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setJobTitle(String newJobTitle){
        this.jobTitle = newJobTitle;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCreatedAt(Date newTime){
        this.createdAt = newTime;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
