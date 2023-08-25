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
import com.bank.OnlinebankingSystem.exception.TransactionFailedToLogException;

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
    public ResponseEntity<String> makeTransaction(Long fromAccountNo, Long toAccountNo, String transactionType, Integer amount, String password)throws MalformedRequestException, TransactionFailedToLogException{
    		Transaction transaction = new Transaction();
    		Optional<Account> fromAccount = accountDao.findById(fromAccountNo);
        	Optional<Account> toAccount = accountDao.findById(toAccountNo);
    		try {
    			transaction.setFromAccount(fromAccount.get());
            	transaction.setToAccount(toAccount.get());
    		}
    		catch(NoSuchElementException e) {
        		throw new MalformedRequestException("Account does not exist");
        	}
    		if(!password.equals(fromAccount.get().getTransactionPassword())) {
    			throw new MalformedRequestException("Incorrect password");
    		}
            transaction.setTransactionType(transactionType);
            transaction.setAmount(amount);
            transaction.setTransactionTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
      
        	
            Account fromAccountToUpdate = accountDao.getReferenceById(fromAccountNo);
            Account toAccountToUpdate = accountDao.getReferenceById(toAccountNo);
            if(fromAccountToUpdate.getBalance()-amount <=0) {
            	throw new MalformedRequestException("Insufficient balance");
            }
            fromAccountToUpdate.setBalance(fromAccountToUpdate.getBalance()-amount);
            toAccountToUpdate.setBalance(toAccountToUpdate.getBalance()+amount);
            try {
            	accountDao.save(fromAccountToUpdate);
            	accountDao.save(toAccountToUpdate);
            	transactionDao.save(transaction);
            }
            catch(Exception e) {
            	throw new TransactionFailedToLogException("Server error: "+e.getMessage());
            }
            
            return ResponseEntity.ok("OK");
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

    public ResponseEntity<String> withdraw(Long fromAccountNo, Integer amount, String password)throws MalformedRequestException, TransactionFailedToLogException {
    	Optional<Account> account = accountDao.findById(fromAccountNo);
    	try {
        	String accountPassword = account.get().getTransactionPassword();
        	if(!accountPassword.equals(password)) {
        		throw new MalformedRequestException("Incorrect password!");
        	}
    	}
    	catch(NoSuchElementException e) {
    		throw new MalformedRequestException("Account does not exist!");
    	}
    	Account fromAccountToUpdate = accountDao.getReferenceById(fromAccountNo);
    	if(fromAccountToUpdate.getBalance()-amount <=0) {
        	throw new MalformedRequestException("Insufficient balance");
        }
    	fromAccountToUpdate.setBalance(fromAccountToUpdate.getBalance()-amount);
    	try {
    		accountDao.save(fromAccountToUpdate);
    	}
    	catch(Exception e) {
    		throw new TransactionFailedToLogException("Server error: "+e.getMessage());
    	}
    	return ResponseEntity.ok("OK");

    }

    //todo withdraw transaction



}
