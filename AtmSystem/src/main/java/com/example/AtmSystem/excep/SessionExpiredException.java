package com.example.AtmSystem.excep;

public class SessionExpiredException extends ATMException {
    public SessionExpiredException(String message) {
        super(message, "SESSION_EXPIRED");
    }

    public SessionExpiredException(String message, String errorCode) {
        super(message, errorCode);
    }
}