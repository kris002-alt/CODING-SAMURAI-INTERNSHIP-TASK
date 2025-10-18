package com.example.AtmSystem.excep;

public class UnauthorizedAccessException extends ATMException {
    public UnauthorizedAccessException(String message) {
        super(message, "UNAUTHORIZED_ACCESS");
    }

    public UnauthorizedAccessException(String message, String errorCode) {
        super(message, errorCode);
    }
}
