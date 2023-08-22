package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;


    @Autowired
    AccountDao accountDao;

    //1. insert transaction -> update balance in both accounts
    @Transactional
    public ResponseEntity<String> makeTransaction(Long fromAccountNo, Long toAccountNo, String transactionType, Integer amount)throws MalformedRequestException, Exception{
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
//        catch(NoSuchElementException e) {
//        	throw new MalformedRequestException("No such account present");
//        }
//        catch(DataIntegrityViolationException e) {
//        	System.out.println("Wow");
//        	throw new MalformedRequestException("Account balance violation or transaction amount violation");
//        }
//        catch(TransactionSystemException e) {
//        	throw new MalformedRequestException("Account balance violation");
//        }
        catch (Exception e){
        	System.out.println(e.getClass().getSimpleName());
        	throw new Exception("Server error: ");
        }
    }


    //2. retrieve transactions for account between date to date
    public ResponseEntity<List<Transaction>> getTransactionsBetween(Long accountNo,Timestamp t1, Timestamp t2)throws MalformedRequestException, Exception{
        try{
        	Optional<Account> account = accountDao.findById(accountNo);
            return ResponseEntity.ok(transactionDao.findTransactionsByAccountAndTimestamps(account.get().getId(),t1,t2));
        }
        catch(NoSuchElementException e) {
        	throw new MalformedRequestException("Account doesn't exist");
        }
        catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }

    }
    
  //2. retrieve transactions for account between date to date
    public List<Transaction> getRecentTransactions(Long accountNo){
        try{
        	Optional<Account> account = accountDao.findById(accountNo);
            List<Transaction> transactions =  transactionDao.findTransactionsByAccount(account.get().getId());
            if(transactions.size()>3){
            return transactions.subList(0,4);}
            else{
                return transactions;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public ResponseEntity<String> withdraw(Long fromAccountNo, Integer amount) {
        Account fromAccountToUpdate = accountDao.getReferenceById(fromAccountNo);
        fromAccountToUpdate.setBalance(fromAccountToUpdate.getBalance()-amount);
        accountDao.save(fromAccountToUpdate);

        return ResponseEntity.ok("OK");

    }

    //todo withdraw transaction



}
