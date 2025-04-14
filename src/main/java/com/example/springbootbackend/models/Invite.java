package com.example.springbootbackend.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

public class Invite {
    @Id
    private String id;

    private String email;
    private String companyId;
    private Role role;
    private String token;
    private String jobTitle;
    private String team;

    private LocalDateTime sentAt;
    private boolean accepted;

    public Invite() {}

    public Invite(String email, String companyId, Role role, String token, String jobTitle, String team) {
        this.email = email;
        this.companyId = companyId;
        this.role = role;
        this.token = token;
        this.jobTitle = jobTitle;
        this.team = team;
        this.sentAt = LocalDateTime.now();
        this.accepted = false;
    }

    // Getters
    public String getEmail() { return email; }
    public String getToken() { return token; }
    public LocalDateTime getSentAt() { return sentAt; }
    public boolean isAccepted() { return accepted; }
    public String getJobTitle() { return jobTitle; }
    public Role getRole() { return role; }
    public String getTeam() { return team; }
    public String getCompanyId() { return companyId; }

    // Setters
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
    public void setEmail(String newEmail) { this.email = newEmail; }
    public void setTeam(String newTeam) { this.team = newTeam; }
}
