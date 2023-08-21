package com.bank.OnlinebankingSystem.Controller;


import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Entity.JwtResponse;
import com.bank.OnlinebankingSystem.Entity.UserDetailsResponse;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    BeneficiaryService beneficiaryService;


    @PostMapping("/login")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> loginAdmin(@RequestBody Map<String,Object> payload){
        try{
            String email = payload.get("email").toString();
            String password = payload.get("password").toString();
            boolean isValid =  adminService.loginUser(email,password);
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
        	String email = payload.get("email").toString();
            ResponseEntity<User> response = adminService.getUserDetails(email);
            User user = response.getBody();
            if(user==null){
                return null;
            }
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

}
