package com.example.AtmSystem.dto;
import com.example.AtmSystem.models.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;

    // Constructors
    public AccountResponse() {}

    public AccountResponse(Long id, String accountNumber, BigDecimal balance,
                           AccountType accountType, Long userId, String userName,
                           LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.userId = userId;
        this.userName = userName;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}