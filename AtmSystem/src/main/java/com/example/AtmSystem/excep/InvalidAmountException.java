package com.example.AtmSystem.excep;

public class InvalidAmountException extends ATMException {
    public InvalidAmountException(String message) {
        super(message, "INVALID_AMOUNT");
    }

    public InvalidAmountException(String message, String errorCode) {
        super(message, errorCode);
    }
}

