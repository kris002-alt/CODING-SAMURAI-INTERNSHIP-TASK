package com.example.AtmSystem.excep;

public class InvalidPinException extends ATMException {
    public InvalidPinException(String message) {
        super(message, "INVALID_PIN");
    }

    public InvalidPinException(String message, String errorCode) {
        super(message, errorCode);
    }
}