package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    UserDao userDao;
	@Autowired
	TransactionService transactionService;

    public ResponseEntity<String> createAccount(String transactionPassword, Long userId, String accountType, Integer balance)throws MalformedRequestException, Exception {
        
    	try {
	    	Account account = new Account();
	        account.setTransactionPassword(transactionPassword);
	        account.setBalance(balance);
	        account.setAccountType(accountType);
	        Optional<User> user = userDao.findById(userId);
	        account.setUser(user.get());
<<<<<<< HEAD
	        account.setIsApproved(false);
=======
//	        account.setApproved(false);
>>>>>>> 562eb60b1004e106fe8fbdf3e7b42a7c87b0252f
	        accountDao.save(account);
	    	return ResponseEntity.ok("OK");
    	}
    	catch(NoSuchElementException e) {
    		throw new MalformedRequestException("Bad request for account creation");
    	}
    	catch(Exception e) {
    		throw new Exception("Server error: "+e.getMessage());
    	}
    }

    public ResponseEntity<AccountSummaryDTO> displayAccount(Long accountNumber)throws MalformedRequestException, Exception {
        
    	try {
    		AccountSummaryDTO accountSummaryDTO = new AccountSummaryDTO();
    		Account account = accountDao.getById(accountNumber);
    		accountSummaryDTO.setBalance(account.getBalance());
    		accountSummaryDTO.setAccountNumber(accountNumber);
    		accountSummaryDTO.setAccountType(account.getAccountType());
			accountSummaryDTO.setTransactionHistory(transactionService.getRecentTransactions(accountNumber));
			System.out.println(accountSummaryDTO.toString());
    		return ResponseEntity.ok(accountSummaryDTO);
    	}
    	catch (EntityNotFoundException e) {
    		throw new MalformedRequestException("AccountNumber does not exist");
    	}
    	catch(Exception e) {
    		throw new Exception("Server error: "+e.getMessage());
    	}
    }

	public List<Account> findByUserId(Long id)throws Exception{
		try {
			return  accountDao.findByUser_Id(id);
		}
		catch(Exception e) {
			throw new Exception("Server error: "+e.getMessage());
    	}
	}

}

