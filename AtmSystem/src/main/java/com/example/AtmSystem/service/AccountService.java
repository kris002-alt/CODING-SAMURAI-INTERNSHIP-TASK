package com.example.AtmSystem.service;

import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.AccountType;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account createAccount(Long userId, AccountType accountType);
    BigDecimal checkBalance(String accountNumber);
    Account getAccountByNumber(String accountNumber);
    List<Account> getUserAccounts(Long userId);
}
