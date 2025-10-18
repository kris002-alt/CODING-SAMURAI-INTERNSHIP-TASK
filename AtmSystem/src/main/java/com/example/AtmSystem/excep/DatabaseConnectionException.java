package com.example.AtmSystem.excep;

public class DatabaseConnectionException extends ATMException {
    public DatabaseConnectionException(String message) {
        super(message, "DATABASE_CONNECTION_ERROR");
    }

    public DatabaseConnectionException(String message, String errorCode) {
        super(message, errorCode);
    }
}
