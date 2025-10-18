package com.example.AtmSystem.models;

import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transactions> transactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Account(Long id, String accountNumber, BigDecimal balance, AccountType accountType, Users user, List<Transactions> transactions) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.user = user;
        this.transactions = transactions;
    }

    // Constructors, getters, setters
}

