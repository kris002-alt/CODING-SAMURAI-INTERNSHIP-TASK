package com.example.AtmSystem.excep;

public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super(message);
    }
}
