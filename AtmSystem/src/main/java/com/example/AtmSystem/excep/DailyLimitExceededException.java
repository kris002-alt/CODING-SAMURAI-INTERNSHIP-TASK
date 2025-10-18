package com.example.AtmSystem.excep;

public class DailyLimitExceededException extends ATMException {
    public DailyLimitExceededException(String message) {
        super(message, "DAILY_LIMIT_EXCEEDED");
    }

    public DailyLimitExceededException(String message, String errorCode) {
        super(message, errorCode);
    }
}
