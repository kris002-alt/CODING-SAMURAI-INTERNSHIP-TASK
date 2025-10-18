package com.example.AtmSystem.excep;

public class AccountLockedException extends ATMException {
    public AccountLockedException(String message) {
        super(message, "ACCOUNT_LOCKED");
    }

    public AccountLockedException(String message, String errorCode) {
        super(message, errorCode);
    }
}
