package com.example.AtmSystem.excep;

public class UserNotFoundException extends ATMException {
    public UserNotFoundException(String message) {
        super(message, "USER_NOT_FOUND");
    }

    public UserNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
