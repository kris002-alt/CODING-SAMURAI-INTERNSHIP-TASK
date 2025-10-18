package com.example.AtmSystem.excep;

public class UserAlreadyExistsException extends ATMException {
    public UserAlreadyExistsException(String message) {
        super(message, "USER_ALREADY_EXISTS");
    }

    public UserAlreadyExistsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
