package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountDao accountDao;

    //1. insert transaction -> update balance in both accounts
    @Transactional
    public ResponseEntity<String> makeTransaction(Long fromAccountNo, Long toAccountNo, String transactionType, Integer amount){
        try{
            Transaction transaction = new Transaction();
            transaction.setTransactionType(transactionType);
            transaction.setAmount(amount);
            Optional<Account> fromAccount = accountDao.findById(fromAccountNo);
            Optional<Account> toAccount = accountDao.findById(toAccountNo);
            transaction.setFromAccount(fromAccount.get());
            transaction.setToAccount(toAccount.get());
            transaction.setTransactionTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
            Account fromAccountToUpdate = accountDao.getReferenceById(fromAccountNo);
            Account toAccountToUpdate = accountDao.getReferenceById(toAccountNo);
            fromAccountToUpdate.setBalance(fromAccountToUpdate.getBalance()-amount);
            accountDao.save(fromAccountToUpdate);
            toAccountToUpdate.setBalance(toAccountToUpdate.getBalance()+amount);
            accountDao.save(toAccountToUpdate);
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
        	Optional<Account> account = accountDao.findById(accountNo);
            return ResponseEntity.ok(transactionDao.findTransactionsByAccountAndTimestamps(account.get().getId(),t1,t2));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }

    }
    
  //2. retrieve transactions for account between date to date
    public List<Transaction> getRecentTransactions(Long accountNo){
        try{
        	Optional<Account> account = accountDao.findById(accountNo);
            List<Transaction> transactions =  transactionDao.findTransactionsByAccount(account.get().getId());
            return transactions.subList(0,4);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //todo withdraw transaction



}
