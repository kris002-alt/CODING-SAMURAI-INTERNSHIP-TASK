package com.example.AtmSystem.dto;

public class AuthResponse {
    private String message;
    private Long userId;
    private String token; // For JWT if you implement it later

    // Constructors, Getters and Setters
    public AuthResponse() {}

    public AuthResponse(String message, Long userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
