package com.example.AtmSystem.excep;
public class InsufficientFundsException extends ATMException {
    public InsufficientFundsException(String message) {
        super(message, "INSUFFICIENT_FUNDS");
    }

    public InsufficientFundsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
