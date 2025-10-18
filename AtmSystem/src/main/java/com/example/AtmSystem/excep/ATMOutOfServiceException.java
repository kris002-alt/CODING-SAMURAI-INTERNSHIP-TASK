package com.example.AtmSystem.excep;

public class ATMOutOfServiceException extends ATMException {
    public ATMOutOfServiceException(String message) {
        super(message, "ATM_OUT_OF_SERVICE");
    }

    public ATMOutOfServiceException(String message, String errorCode) {
        super(message, errorCode);
    }
}