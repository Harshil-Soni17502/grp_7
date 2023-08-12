package com.bank.OnlinebankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.OnlinebankingSystem.Service.UserService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;

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
                                     ){
    	System.out.println("pay"+payload.get("title"));
    	System.out.println("pay"+payload.get("firstName"));
    	System.out.println("pay"+payload.get("dateOfBirth"));
    	System.out.println("pay"+payload.get("aadharCardNumber"));

    	//return ResponseEntity.ok("sd");
        return userService.createUser(
               payload.get("title").toString(),
                payload.get("firstName").toString(),
                payload.get("lastName").toString(),
                payload.get("email").toString(),
                payload.get("password").toString(),
                payload.get("fullPermanentAddress").toString(),
                payload.get("fullResidentialAddress").toString(),
                payload.get("occupation").toString(),
                Double.valueOf( payload.get("totalGrossCompensation").toString()),
                payload.get("aadharCardNumber").toString(),
                payload.get("dateOfBirth").toString(),
                payload.get("mobileNumber").toString()
                );
    }

    @PostMapping("/login")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> loginUser( @RequestBody Map<String,Object> payload){
        return userService.loginUser(
                payload.get("email").toString(),
                payload.get("password").toString()
        );
    }
    //login

}
