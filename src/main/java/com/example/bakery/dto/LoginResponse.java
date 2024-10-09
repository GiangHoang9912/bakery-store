package com.example.bakery.dto;

public class LoginResponse {
    private String token;
    private String role;
    private String fullname;
    private String email;

    // Constructor
    public LoginResponse(String token, String role, String fullname, String email) {
        this.token = token;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
