package com.example.AtmSystem.service;

import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.TransactionType;
import com.example.AtmSystem.models.Transactions;
import com.example.AtmSystem.repo.AccountRepository;
import com.example.AtmSystem.repo.TransactionRepository;
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
        // Validate amount
        validateAmount(amount);

        // Check withdrawal limit
        if (amount.compareTo(maxWithdrawal) > 0) {
            throw new TransactionLimitExceededException(
                    "Withdrawal amount exceeds maximum limit of " + maxWithdrawal
            );
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available balance: " + account.getBalance()
            );
        }

        // Update account balance
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        // Create transaction record
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Cash withdrawal from account: " + accountNumber);
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transactions deposit(String accountNumber, BigDecimal amount) {
        // Validate amount
        validateAmount(amount);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        // Update account balance
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        // Create transaction record
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Cash deposit to account: " + accountNumber);
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transactions transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        // Validate amount
        validateAmount(amount);

        // Check transfer limit
        if (amount.compareTo(maxTransfer) > 0) {
            throw new TransactionLimitExceededException(
                    "Transfer amount exceeds maximum limit of " + maxTransfer
            );
        }

        // Check if source and destination accounts are different
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new InvalidAmountException("Cannot transfer to the same account");
        }

        // Find source account
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Source account not found: " + fromAccountNumber));

        // Find destination account
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Destination account not found: " + toAccountNumber));

        // Check sufficient funds in source account
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds for transfer. Available balance: " + fromAccount.getBalance()
            );
        }

        // Perform transfer (atomic operation)
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create transaction record for the transfer (debit from source account)
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription(String.format(
                "Transfer to account: %s. From account: %s",
                toAccountNumber, fromAccountNumber
        ));
        transaction.setAccount(fromAccount);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transactions> getTransactionHistory(String accountNumber) {
        // Verify account exists
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        return transactionRepository.findByAccountAccountNumberOrderByTimestampDesc(accountNumber);
    }

    // Helper method to validate amount
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (amount.compareTo(minAmount) < 0) {
            throw new InvalidAmountException("Amount must be at least " + minAmount);
        }
    }

    // Additional method to get account balance
    public BigDecimal getAccountBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        return account.getBalance();
    }
}

