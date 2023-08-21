package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminService {
	public ResponseEntity<String> loginAdmin(String adminId, String password) throws MalformedRequestException, Exception {
		try {
			if(adminId.equals("admin") && password.equals("password")){
				System.out.println("valid");
				return ResponseEntity.ok("Login Successfull");
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
