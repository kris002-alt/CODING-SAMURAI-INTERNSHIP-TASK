package com.example.AtmSystem.controller;

import com.example.AtmSystem.dto.BalanceResponse;
import com.example.AtmSystem.dto.TransactionRequest;
import com.example.AtmSystem.dto.TransactionResponse;
import com.example.AtmSystem.models.Transactions;
import com.example.AtmSystem.service.AccountService;
import com.example.AtmSystem.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BalanceResponse> checkBalance(@PathVariable String accountNumber) {
        BigDecimal balance = accountService.checkBalance(accountNumber);

        BalanceResponse response = new BalanceResponse();
        response.setAccountNumber(accountNumber);
        response.setBalance(balance);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(
            @RequestBody @Valid TransactionRequest request) {

        Transactions     transaction = transactionService.withdraw(
                request.getAccountNumber(), request.getAmount());

        TransactionResponse response = mapToTransactionResponse(transaction);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(
            @RequestBody @Valid TransactionRequest request) {

        Transactions transaction = transactionService.deposit(
                request.getAccountNumber(), request.getAmount());

        TransactionResponse response = mapToTransactionResponse(transaction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(
            @PathVariable String accountNumber) {

        List<Transactions> transactions = transactionService.getTransactionHistory(accountNumber);
        List<TransactionResponse> responses = transactions.stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    private TransactionResponse mapToTransactionResponse(Transactions transaction) {
        // Mapping logic
        return   new TransactionResponse();
    }
}
