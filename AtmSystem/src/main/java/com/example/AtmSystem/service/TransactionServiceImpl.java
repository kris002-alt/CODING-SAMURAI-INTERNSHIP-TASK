package com.example.AtmSystem.service;

import com.example.AtmSystem.excep.*;
import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.TransactionType;
import com.example.AtmSystem.models.Transactions;
import com.example.AtmSystem.repo.AccountRepository;
import com.example.AtmSystem.repo.TransactionRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${atm.max-withdrawal:50000}")
    private BigDecimal maxWithdrawal;

    @Value("${atm.max-transfer:100000}")
    private BigDecimal maxTransfer;

    @Value("${atm.min-amount:1}")
    private BigDecimal minAmount;

    @Override
    public Transactions withdraw(String accountNumber, BigDecimal amount) {
        try {
            // Validate amount
            validateAmount(amount);

            // Check withdrawal limit
            if (amount.compareTo(maxWithdrawal) > 0) {
                throw new TransactionLimitExceededException(
                        "Withdrawal amount " + amount + " exceeds maximum limit of " + maxWithdrawal
                );
            }

            Account account = getActiveAccount(accountNumber);

            if (account.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException(
                        "Insufficient funds. Requested: " + amount + ", Available: " + account.getBalance()
                );
            }

            // Update account balance
            BigDecimal newBalance = account.getBalance().subtract(amount);
            account.setBalance(newBalance);
            accountRepository.save(account);

            // Create transaction record
            return (Transactions) createTransaction(account, amount, TransactionType.WITHDRAWAL,
                    "Cash withdrawal from account: " + accountNumber);

        } catch (Exception e) {
            if (!(e instanceof ATMException)) {
                throw new InvalidTransactionException("Withdrawal failed: " + e.getMessage());
            }
            try {
                throw e;
            } catch (AccountNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Transactions deposit(String accountNumber, BigDecimal amount) {
        try {
            // Validate amount
            validateAmount(amount);

            Account account = getActiveAccount(accountNumber);

            // Update account balance
            BigDecimal newBalance = account.getBalance().add(amount);
            account.setBalance(newBalance);
            accountRepository.save(account);

            // Create transaction record
            return (Transactions) createTransaction(account, amount, TransactionType.DEPOSIT,
                    "Cash deposit to account: " + accountNumber);

        } catch (Exception e) {
            if (!(e instanceof ATMException)) {
                throw new InvalidTransactionException("Deposit failed: " + e.getMessage());
            }
            try {
                throw e;
            } catch (AccountNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Transactions transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        try {
            // Validate amount
            validateAmount(amount);

            // Check transfer limit
            if (amount.compareTo(maxTransfer) > 0) {
                throw new TransactionLimitExceededException(
                        "Transfer amount " + amount + " exceeds maximum limit of " + maxTransfer
                );
            }

            // Check if source and destination accounts are different
            if (fromAccountNumber.equals(toAccountNumber)) {
                throw new InvalidTransactionException("Cannot transfer to the same account");
            }

            // Find source account
            Account fromAccount = getActiveAccount(fromAccountNumber);

            // Find destination account
            Account toAccount = getActiveAccount(toAccountNumber);

            // Check sufficient funds in source account
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException(
                        "Insufficient funds for transfer. Requested: " + amount + ", Available: " + fromAccount.getBalance()
                );
            }

            // Perform transfer (atomic operation)
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            // Create transaction record for the transfer (debit from source account)
            return (Transactions) createTransaction(fromAccount, amount, TransactionType.TRANSFER,
                    String.format("Transfer to account: %s. From account: %s", toAccountNumber, fromAccountNumber));

        } catch (Exception e) {
            if (!(e instanceof ATMException)) {
                throw new InvalidTransactionException("Transfer failed: " + e.getMessage());
            }
            try {
                throw e;
            } catch (AccountNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<Transactions> getTransactionHistory(String accountNumber) {
        // Verify account exists and is active
        try {
            getActiveAccount(accountNumber);
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }

        return transactionRepository.findByAccountAccountNumberOrderByTimestampDesc(accountNumber);
    }

    // Helper method to validate amount
    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidAmountException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (amount.compareTo(minAmount) < 0) {
            throw new InvalidAmountException("Amount must be at least " + minAmount);
        }
    }

    // Helper method to get active account
    private Account getActiveAccount(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        // You can add additional checks here, like if account is locked/active
        // if (account.isLocked()) {
        //     throw new AccountLockedException("Account is locked: " + accountNumber);
        // }

        return account;
    }

    // Helper method to create transaction
    private Transaction createTransaction(Account account, BigDecimal amount, TransactionType type, String description) {
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription(description);
        transaction.setAccount(account);

        return (Transaction) transactionRepository.save(transaction);
    }

    // Additional method to get account balance
    public BigDecimal getAccountBalance(String accountNumber) throws AccountNotFoundException {
        Account account = getActiveAccount(accountNumber);
        return account.getBalance();
    }
}