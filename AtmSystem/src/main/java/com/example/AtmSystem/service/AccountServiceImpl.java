package com.example.AtmSystem.service;


import com.example.AtmSystem.dto.AccountResponse;
import com.example.AtmSystem.dto.BalanceResponse;
import com.example.AtmSystem.excep.UserNotFoundException;
import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.AccountType;
import com.example.AtmSystem.models.Users;
import com.example.AtmSystem.repo.AccountRepository;
import com.example.AtmSystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Account createAccount(Long userId, AccountType accountType) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(accountType);
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        return account.getBalance();
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).get();
    }

    @Override
    public List<Account> getUserAccounts(Long userId) {
        return List.of();
    }
}
