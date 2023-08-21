package com.bank.OnlinebankingSystem.Controller;


import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.UserService;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.Entity.Account;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
   
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
    
    @GetMapping("/getPendingAccounts")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<List<Account>> getPendingAccounts() throws MalformedRequestException, Exception{
    	return adminService.getPendingAccounts();
    }

}
