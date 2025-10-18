package com.example.AtmSystem.excep;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public Object getErrorCode() {
        return "userNotFound";
    }
}
