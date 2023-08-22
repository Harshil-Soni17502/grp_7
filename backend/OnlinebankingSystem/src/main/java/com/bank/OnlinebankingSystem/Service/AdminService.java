package com.bank.OnlinebankingSystem.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {

	@Autowired
	AccountDao accountDao;
	
    public boolean loginUser(String email, String password) {

        if(email.equals("admin2@gmail.com") && password.equals("pass2")){
            return true;
        }

        return false;

    }
    
    public ResponseEntity<List<Account>> getPendingAccounts() throws Exception{
    	try {
    		return ResponseEntity.ok().body(accountDao.findAllByIsApproved(false));
    	}
    	catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }
    }
    
    public ResponseEntity<String> setStatus(Long accountId) throws MalformedRequestException, EntityExistsException, Exception{
    	try {
    		Optional<Account> account = accountDao.findById(accountId);
    		System.out.println(account.get().getIsApproved());
    		if(!account.get().getIsApproved()) {
    			Account changeStatus = accountDao.getReferenceById(accountId);
    			changeStatus.setIsApproved(true);
    			accountDao.save(changeStatus);
    			return ResponseEntity.ok().body("Account approved.");
    		}
    		return ResponseEntity.ok().body("Account is already approved");
    	}
    	catch (NoSuchElementException e) {
    		throw new MalformedRequestException("Account ID does not exist");
    	}
    	catch(Exception e) {
        	throw new MalformedRequestException("insert beneficiary request bad");
        }
    }
}