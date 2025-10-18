package com.example.AtmSystem.excep;

public class TransactionLimitExceededException extends ATMException {
    public TransactionLimitExceededException(String message) {
        super(message, "TRANSACTION_LIMIT_EXCEEDED");
    }

    public TransactionLimitExceededException(String message, String errorCode) {
        super(message, errorCode);
    }
}
