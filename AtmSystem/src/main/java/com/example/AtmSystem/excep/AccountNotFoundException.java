package com.example.AtmSystem.excep;



public class AccountNotFoundException extends ATMException {
    public AccountNotFoundException(String message) {
        super(message, "ACCOUNT_NOT_FOUND");
    }

    public AccountNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}

