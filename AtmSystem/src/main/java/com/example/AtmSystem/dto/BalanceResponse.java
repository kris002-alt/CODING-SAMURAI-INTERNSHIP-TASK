package com.example.AtmSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceResponse {
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime timestamp;

    // Constructors, Getters and Setters
    public BalanceResponse() {}

    public BalanceResponse(String accountNumber, BigDecimal balance, LocalDateTime timestamp) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.timestamp = timestamp;
    }


    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
