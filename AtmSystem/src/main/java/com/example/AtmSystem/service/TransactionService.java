package com.example.AtmSystem.service;

import com.example.AtmSystem.models.Transactions;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transactions withdraw(String accountNumber, BigDecimal amount);
    Transactions deposit(String accountNumber, BigDecimal amount);
    Transactions transfer(String fromAccount, String toAccount, BigDecimal amount);
    List<Transactions> getTransactionHistory(String accountNumber);
}
