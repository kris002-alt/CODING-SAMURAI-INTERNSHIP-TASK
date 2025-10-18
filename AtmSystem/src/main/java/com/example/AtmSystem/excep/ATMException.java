package com.example.AtmSystem.excep;

public class ATMException extends RuntimeException {
    private String errorCode;

    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}