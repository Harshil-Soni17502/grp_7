package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    UserDao userDao;

    public ResponseEntity<String> createAccount(String transactionPassword, Long userId, String accountType) {
        
    	try {
	    	Account account = new Account();
	        account.setTransactionPassword(transactionPassword);
	        account.setBalance(100);
	        account.setAccountType(accountType);
	        Optional<User> user = userDao.findById(userId);
	        account.setUser(user.get());
	        accountDao.save(account);
	    	return ResponseEntity.ok("OK");
    	}
    	catch(Exception e) {
        	return ResponseEntity.ok(e.getMessage());
    	}
    }

    public ResponseEntity<AccountSummaryDTO> displayAccount(Long accountNumber) {
        
    	try {
    		AccountSummaryDTO accountSummaryDTO = new AccountSummaryDTO();
    		Account account = accountDao.getById(accountNumber);
    		accountSummaryDTO.setBalance(account.getBalance());
    		accountSummaryDTO.setAccountNumber(accountNumber);
    		accountSummaryDTO.setAccountType(account.getAccountType());
    		return ResponseEntity.ok(accountSummaryDTO);
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(500).body(null);  
    	}
    }

	public List<Account> findByUserId(Long id){
		return  accountDao.findByUser_Id(id);
	}

}

