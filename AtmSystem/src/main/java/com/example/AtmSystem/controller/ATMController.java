package com.example.AtmSystem.controller;
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

        Transaction transaction = transactionService.withdraw(
                request.getAccountNumber(), request.getAmount());

        TransactionResponse response = mapToTransactionResponse(transaction);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(
            @RequestBody @Valid TransactionRequest request) {

        Transaction transaction = transactionService.deposit(
                request.getAccountNumber(), request.getAmount());

        TransactionResponse response = mapToTransactionResponse(transaction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(
            @PathVariable String accountNumber) {

        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);
        List<TransactionResponse> responses = transactions.stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        // Mapping logic
    }
}
