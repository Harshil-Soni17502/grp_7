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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.Repository.AccountDao;


@Service
public class AdminService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	UserDao userdao;

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
    
    public ResponseEntity<User> getUserDetails(String email) throws MalformedRequestException, Exception {
		try {
			User user = userdao.findByEmailId(email);
			if(user!=null){
				System.out.println("valid");
				return ResponseEntity.ok(user);
			}
			else{
				System.out.println("invalid");
				throw new MalformedRequestException("Invalid Credentials");
			}
		} catch (Exception e) {
			throw new Exception("Server error: " + e.getMessage());
		}
//		catch (Exception e) {
////			throw new Exception("Server error: "+e.getMessage());
////		}
		
	}
    
    public ResponseEntity<User> getUserDetailsById(Long userId) throws MalformedRequestException, Exception {
		try {
			Optional<User> user = userdao.findById(userId);
			if(user.isPresent()){
				System.out.println("valid");
				return ResponseEntity.ok(user.get());
			}
			else{
				System.out.println("invalid");
				throw new MalformedRequestException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new Exception("Server error: "+e.getMessage());
		}
		
	}
    
    
    public ResponseEntity<Account> getAccountDetails(Long accountId) throws MalformedRequestException, Exception {
		try {
			Optional<Account> account = accountDao.findById(accountId);
			
			if(account.isPresent()){
				System.out.println("valid");
				return ResponseEntity.ok(account.get());
			}
			else{
				System.out.println("invalid");
				throw new MalformedRequestException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new Exception("Server error: "+e.getMessage());
		}
		
	}
}
//=======
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminService {
//
//
//
//    public boolean loginUser(String email, String password) {
//
//        if(email.equals("admin2@gmail.com") && password.equals("pass2")){
//            return true;
//        }
//
//        return false;
//
//    }
//>>>>>>> db3c05ba5535fb0be58ccf5f0949ffc14986e375
//}
