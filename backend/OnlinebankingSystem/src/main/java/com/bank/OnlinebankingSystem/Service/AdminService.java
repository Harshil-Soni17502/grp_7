package com.bank.OnlinebankingSystem.Service;

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
	UserDao userdao;
	
	@Autowired
	AccountDao accountdao;

    public boolean loginUser(String email, String password) {

        if(email.equals("admin2@gmail.com") && password.equals("pass2")){
            return true;
        }

        return false;

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
		}
		catch (Exception e) {
			throw new Exception("Server error: "+e.getMessage());
		}
		
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
			Optional<Account> account = accountdao.findById(accountId);
			
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
