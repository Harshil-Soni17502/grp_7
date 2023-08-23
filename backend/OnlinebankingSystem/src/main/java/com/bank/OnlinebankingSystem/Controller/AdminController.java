package com.bank.OnlinebankingSystem.Controller;


import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Entity.JwtResponse;
import com.bank.OnlinebankingSystem.Entity.UserDetailsResponse;
import com.bank.OnlinebankingSystem.Entity.AccountDetailsResponse;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
import com.bank.OnlinebankingSystem.Service.TransactionService;
import com.bank.OnlinebankingSystem.Service.AccountService;
import com.bank.OnlinebankingSystem.Service.UserService;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
//<<<<<<< HEAD
	
//
//	@Autowired
//    AdminService adminService;
//
//
//	@PostMapping("/login")
//	@CrossOrigin(origins ="http://localhost:3000")
//	public ResponseEntity<String> loginAdmin( @RequestBody Map<String,Object> payload) throws MalformedRequestException, EntityExistsException, Exception {
//		System.out.print("loginAdmin controller called");
//		try {
//      return adminService.loginAdmin(
//              payload.get("adminId").toString(),
//              payload.get("password").toString()
//      );
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//            throw new Exception("Server error: "+e.getMessage());
//		}
//  }
//=======

    @Autowired
    AdminService adminService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    BeneficiaryService beneficiaryService;
    
    @Autowired
    TransactionService transactionService;


    @PostMapping("/login")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> loginAdmin(@RequestBody Map<String,Object> payload){
        try{
            String email = payload.get("email").toString();
            String password = payload.get("password").toString();
            //boolean isValid =  adminService.loginAdmin(email,password).getBody();
            boolean isValid =  true;
            if(isValid){
                return ResponseEntity.ok("Valid");

            }
                return ResponseEntity.ok("Invalid");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    
    @PostMapping("/getUserDetails")
    @CrossOrigin(origins ="http://localhost:3000")
    public UserDetailsResponse getUserDetails(@RequestBody Map<String,Object> payload) throws MalformedRequestException, Exception{
        try{
        	
        	String email = payload.get("emailId").toString();
        	System.out.println(email);
            ResponseEntity<User> response = adminService.getUserDetails(email);
//            System.out.println(response);
            
            if(response==null){
                return null;
            }
            User user = response.getBody();
            final UserDetails userDetails =
                    userService.loadUserByUsername(email);

            //get username
            String userName = user.getFirstName()+" "+user.getLastName();
            //get userId
            Long userId = user.getId();
            String dateOfBirth = user.getDOB();
            String mobileNumber = user.getMobileNumber();
            String permanentAddress = user.getPermanentAddress();
            String residentialAddress = user.getResidentialAddress();
            String occupation = user.getOccupation();
            double totalGrossIncome = user.getTotalGrossIncome();
            String aadharNumber = user.getAadharNumber();
            
            //get accounts
            List<Account> accounts = accountService.findByUserId(userId);
            //get beneficiary for each account
            Map<Long, List<Beneficiary>> accountBeneficiaryMap = new HashMap<>();
            for(Account account: accounts){
                List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesOf(account.getId()).getBody();
                accountBeneficiaryMap.put(account.getId(),beneficiaries);
            }
            return new UserDetailsResponse(userId,userName,dateOfBirth,email,mobileNumber,permanentAddress,residentialAddress,occupation,totalGrossIncome,aadharNumber,accounts,accountBeneficiaryMap);

        }
        catch (Exception e){
        	e.printStackTrace();
            throw new Exception("Server error: "+e.getMessage());
        }
    }

    
    
    @PostMapping("/getAccountDetails")
    @CrossOrigin(origins ="http://localhost:3000")
    public AccountDetailsResponse getAccountDetails(@RequestBody Map<String,Object> payload) throws MalformedRequestException, Exception{
        try{
        	Long accountId = Long.parseLong(payload.get("accountNumber").toString());
        	LocalDateTime now = LocalDateTime.now();
        	LocalDateTime d1 = now.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
        	LocalDateTime d2 = now.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
        	
        	Timestamp t1 = Timestamp.valueOf(d1);
        	Timestamp t2 = Timestamp.valueOf(d2);
        	
        	
            ResponseEntity<Account> response = adminService.getAccountDetails(accountId);
            Account account = response.getBody();
            if(account==null){
                return null;
            }
            
            String accountType = account.getAccountType();
            User user = account.getUser();
            String userEmail = user.getEmailId();
            String balance = account.getBalance().toString();
            Boolean isApproved = account.getIsApproved();
            
            //get accounts
            ResponseEntity<List<Transaction>> transactionResponse = transactionService.getTransactionsBetween(accountId,t1,t2);
            List<Transaction> transactions = transactionResponse.getBody();
//            //get beneficiary for each account
//            Map<Long, List<Beneficiary>> accountBeneficiaryMap = new HashMap<>();
//            for(Account account: accounts){
//                List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesOf(account.getId()).getBody();
//                accountBeneficiaryMap.put(account.getId(),beneficiaries);
//            }
            return new AccountDetailsResponse(accountType,balance,userEmail,isApproved,transactions);

        }
        catch (Exception e){
        	e.printStackTrace();
            throw new Exception("Server error: "+e.getMessage());
        }
    }

}
