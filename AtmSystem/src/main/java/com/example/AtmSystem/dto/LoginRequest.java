package com.example.AtmSystem.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "PIN is required")
    private String pin;

    // Constructors
    public LoginRequest() {}

    public LoginRequest(String username, String pin) {
        this.username = username;
        this.pin = pin;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}
