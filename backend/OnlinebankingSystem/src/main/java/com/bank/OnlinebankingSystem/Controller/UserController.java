package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.Entity.*;
import com.bank.OnlinebankingSystem.Service.AccountService;
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
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    BeneficiaryService beneficiaryService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/test")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> test(){
        try {

            return ResponseEntity.ok("works");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error from server");
        }
    }


    //createUser
    @PostMapping("/create")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> createUser(@RequestBody Map<String,Object> payload
//    		@RequestParam String title,
//                                     @RequestParam String firstName,
//                                     @RequestParam String lastName,
//                                     @RequestParam String email,
//                                     @RequestParam String password,
//                                     @RequestParam String fullPermanentAddress,
//                                     @RequestParam String fullResidentialAddress,
//                                     @RequestParam String occupation,
//                                     @RequestParam Double totalGrossCompensation,
//                                     @RequestParam String aadharCardNumber,
//                                     @RequestParam String dateOfBirth,
//                                     @RequestParam String mobileNumber
                                     )throws MalformedRequestException, EntityExistsException, Exception{
//            System.out.println("pay" + payload.get("title"));
//            System.out.println("pay" + payload.get("firstName"));
//            System.out.println("pay" + payload.get("dateOfBirth"));
//            System.out.println("pay" + payload.get("aadharCardNumber"));

            //return ResponseEntity.ok("sd");
        	System.out.println("user creating");
            return userService.createUser(
                    payload.get("title").toString(),
                    payload.get("firstName").toString(),
                    payload.get("lastName").toString(),
                    payload.get("email").toString(),
                    payload.get("password").toString(),
                    payload.get("fullPermanentAddress").toString(),
                    payload.get("fullResidentialAddress").toString(),
                    payload.get("occupation").toString(),
                    Double.valueOf(payload.get("totalGrossCompensation").toString()),
                    payload.get("aadharCardNumber").toString(),
                    payload.get("dateOfBirth").toString(),
                    payload.get("mobileNumber").toString()
            );
        
    }

//    @PostMapping("/login")
//    @CrossOrigin(origins ="http://localhost:3000")
//    public ResponseEntity<String> loginUser( @RequestBody Map<String,Object> payload){
//        return userService.loginUser(
//                payload.get("email").toString(),
//                payload.get("password").toString()
//        );
//    }


    //login
    @PostMapping("/login")
    @CrossOrigin(origins ="http://localhost:3000")
    public JwtResponse loginUser(@RequestBody JwtRequest jwtRequest) throws MalformedRequestException, Exception{

        try{
            System.out.println("login from user controller");
            ResponseEntity<User> response = userService.loginUser(jwtRequest.getEmail(), jwtRequest.getPassword());
            User user = response.getBody();
            if(user==null){
                return null;
            }
            System.out.println("processing JWT");

            Authentication auth =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );

            System.out.println("auth manager complete");
            System.out.println(auth.isAuthenticated());
            System.out.println(auth.getDetails());
            final UserDetails userDetails =
                    userService.loadUserByUsername(jwtRequest.getEmail());
            System.out.println("user details complete");
            final String token = jwtUtility.generateToken(userDetails);

            //get username
            String userName = user.getFirstName()+" "+user.getLastName();
            //get userId
            Long userId = user.getId();
            //get accounts
            List<Account> accounts = accountService.findByUserId(userId);
            //get beneficiary for each account
            Map<Long, List<Beneficiary>> accountBeneficiaryMap = new HashMap<>();
            for(Account account: accounts){
                List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesOf(account.getId()).getBody();
                accountBeneficiaryMap.put(account.getId(),beneficiaries);
            }
            Date TimeOfExpiry = jwtUtility.getExpirationDateFromToken(token);
            System.out.println("returning JWT");
            return new JwtResponse(token,userId,userName,accounts,accountBeneficiaryMap,TimeOfExpiry);
            //return new JwtResponse(token);

        } catch( Exception e){
            e.printStackTrace();
            throw new Exception("Server error: "+e.getMessage());
        }


    }

}
