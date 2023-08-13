package com.bank.OnlinebankingSystem.Repository;


import com.bank.OnlinebankingSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction,Long> {
    //1. insert transaction -> update balance in both accounts

    //2. retrieve transactions for account between date to date
    @Query("SELECT t FROM Transaction t WHERE (t.fromAccountNo = ?1 OR t.toAccountNo = ?1) AND t.transactionTimestamp BETWEEN ?2 AND ?3")
    List<Transaction> findTransactionsByAccountNoAndTimestamps(Long accountNo, Timestamp startTimestamp, Timestamp endTimestamp);
}
