package com.example.AtmSystem.dto;

import com.example.AtmSystem.models.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountDTO {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    private Double balance;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    // Constructors
    public AccountDTO() {}

    public AccountDTO(String accountNumber, Double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
}