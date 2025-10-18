package com.example.AtmSystem.excep;

public class InvalidTransactionException extends ATMException {
    public InvalidTransactionException(String message) {
        super(message, "INVALID_TRANSACTION");
    }

    public InvalidTransactionException(String message, String errorCode) {
        super(message, errorCode);
    }
}