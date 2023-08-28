package com.bank.OnlinebankingSystem.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.util.ArrayList;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;

    public boolean loginUser(String email, String password) {

        if(email.equals("admin2@gmail.com") && password.equals("pass2")){
            return true;
        }

        return false;

    }
    
//    @Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//		//User user =  userdao.findByEmailId(email);
//
//		return new org.springframework.security.core.userdetails.User("admin2@gmail.com","pass2",new ArrayList<>());
//		//return new org.springframework.security.core.userdetails.User("admin","pwd",new ArrayList<>());
//	}
    
    public List<Account> getPendingAccounts() throws Exception{
    	try {
    		System.out.println("here");
    		return accountDao.findAllByIsApproved("pending");
    	}
    	catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }
    }
    
    public ResponseEntity<String> setStatus(Long accountId) throws MalformedRequestException, EntityExistsException, Exception{
    	try {
    		Optional<Account> account = accountDao.findById(accountId);
    		System.out.println(account.get().getIsApproved());
    		if(account.get().getIsApproved().equals("pending")) {
    			Account changeStatus = accountDao.getReferenceById(accountId);
    			changeStatus.setIsApproved("approved");
    			accountDao.save(changeStatus);
    			return ResponseEntity.ok().body("Account approved.");
    		}
    		if(account.get().getIsApproved().equals("rejected")) {
    			return ResponseEntity.ok().body("Account is rejected so it can't be approved");
    		}
    		return ResponseEntity.ok().body("Account is already approved");
    	}
    	catch (NoSuchElementException e) {
    		throw new MalformedRequestException("Account ID does not exist");
    	}
    	catch(Exception e) {
        	throw new MalformedRequestException("approve account request bad");
        }
    }
    
    public ResponseEntity<String> reject(Long accountId) throws MalformedRequestException, EntityExistsException, Exception{
    	try {
    		Optional<Account> account = accountDao.findById(accountId);
    		System.out.println("hii from reject " + account.get().getIsApproved());
    		if(account.get().getIsApproved().equals("pending") || account.get().getIsApproved().equals("approved")) {
    			Account changeStatus = accountDao.getReferenceById(accountId);
    			changeStatus.setIsApproved("rejected");
    			accountDao.save(changeStatus);
    			return ResponseEntity.ok().body("Account Rejected");
    		}
    		
    		
    		return ResponseEntity.ok().body("Account is already rejected");
    	}
    	catch (NoSuchElementException e) {
    		throw new MalformedRequestException("Account ID does not exist");
    	}
    	catch(Exception e) {
    		throw new MalformedRequestException("reject account request bad");
    	}
    }
    
    public ResponseEntity<User> getUserDetails(String email) throws MalformedRequestException, Exception {
    	System.out.println("email:"+email);
		try {
			
			User user = userdao.findByEmailId(email);
			System.out.println("user:"+user);
			if(user!=null){
				System.out.println("valid");
				return ResponseEntity.ok(user);
			}
			else{
				System.out.println("invalid user credentials");
				return null;
//				throw new MalformedRequestException("Invalid Credentials");
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
				return null;
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
				return null;
//				throw new MalformedRequestException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new Exception("Server error: "+e.getMessage());
		}
		
	}
    public List<User> getAllUsers(Integer num1, Integer num2)throws MalformedRequestException{
		if(num1==null && num2==null) {
			List<User> users = userdao.findAll();
			return users;
		}
		else if(num1!=null && num2!=null) {
			System.out.println(num1+" "+num2);
			Page<User> users = userdao.findAll(PageRequest.of(num2-1, num1, Sort.by(Sort.Direction.ASC, "emailId")));
			return users.getContent();
		}
		else if(num1==null) {
			throw new MalformedRequestException("Number of records cannot be null");
		}
		else {
			throw new MalformedRequestException("Offset cannot be null");
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
