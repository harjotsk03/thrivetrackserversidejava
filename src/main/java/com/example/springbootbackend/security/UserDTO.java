package com.example.springbootbackend.security;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.example.springbootbackend.models.Role;
import com.example.springbootbackend.models.User;

public class UserDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private String profilePhoto;
    @Id
    private String companyId;
    private Role role;
    private String team;
    private String jobTitle;
    private String companyName;
    private Date createdAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profilePhoto = user.getProfilePhoto();
        this.companyId = user.getCompanyId();
        this.role = user.getRole();
        this.team = user.getTeam();
        this.jobTitle = user.getJobTitle();
        this.createdAt = user.getCreatedAt();
        this.companyName = user.getCompanyName();
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getProfilePhoto(){
        return this.profilePhoto;
    }
    public String getCompanyId(){
        return this.companyId;
    }
    public String getTeam(){
        return this.team;
    }
    public String getJobTitle(){
        return this.jobTitle;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public Role getRole(){
        return this.role;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
