package com.example.AtmSystem.repo;

import com.example.AtmSystem.models.Transactions;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByAccountIdOrderByTimestampDesc(Long accountId);
    List<Transactions> findByAccountAccountNumberOrderByTimestampDesc(String accountNumber);
}
