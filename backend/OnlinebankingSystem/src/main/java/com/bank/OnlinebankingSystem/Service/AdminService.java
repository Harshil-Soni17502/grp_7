package com.bank.OnlinebankingSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.Repository.UserDao;


@Service
public class AdminService {

	@Autowired
	UserDao userdao;

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
}
