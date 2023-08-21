package com.bank.OnlinebankingSystem.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import java.util.List;

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
}
