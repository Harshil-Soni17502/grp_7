package com.bank.OnlinebankingSystem.Repository;


import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction,Long> {
    //1. insert transaction -> update balance in both accounts

    //2. retrieve transactions for account between date to date
    @Query("SELECT t FROM Transaction t WHERE (t.fromAccount.id = ?1 OR t.toAccount.id= ?1) AND t.transactionTimestamp BETWEEN ?2 AND ?3")
    List<Transaction> findTransactionsByAccountAndTimestamps(Long accountId, Timestamp startTimestamp, Timestamp endTimestamp);
}
