package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;

    //1. insert transaction -> update balance in both accounts
    public ResponseEntity<String> makeTransaction(Long fromAccountNo, Long toAccountNo, String transactionType, Integer amount){
        try{
            Transaction transaction = new Transaction();
            transaction.setTransactionType(transactionType);
            transaction.setAmount(amount);
            transaction.setToAccountNo(toAccountNo);
            transaction.setFromAccountNo(fromAccountNo);
            transaction.setTransactionTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
            transactionDao.save(transaction);
            return ResponseEntity.ok("OK");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //2. retrieve transactions for account between date to date
    public ResponseEntity<List<Transaction>> getTransactionsBetween(Long accountNo,Timestamp t1, Timestamp t2){
        try{
            return ResponseEntity.ok(transactionDao.findTransactionsByAccountNoAndTimestamps(accountNo,t1,t2));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }

    }



}
