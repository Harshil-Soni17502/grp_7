package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.Entity.*;
import com.bank.OnlinebankingSystem.Service.AccountService;
import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.bank.OnlinebankingSystem.Service.UserService;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
    AdminService adminService;
	
	
	@PostMapping("/login")
	@CrossOrigin(origins ="http://localhost:3000")
	public ResponseEntity<String> loginAdmin( @RequestBody Map<String,Object> payload) throws MalformedRequestException, EntityExistsException, Exception {
		System.out.print("loginAdmin controller called");
		try {
      return adminService.loginAdmin(
              payload.get("adminId").toString(),
              payload.get("password").toString()
      );
		}
		catch(Exception e) {
			e.printStackTrace();
            throw new Exception("Server error: "+e.getMessage());
		}
  }
}
