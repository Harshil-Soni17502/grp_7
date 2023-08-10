package com.bank.OnlinebankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.OnlinebankingSystem.Service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;

    //createUser
    @PostMapping("/create")
    public void createUser(@RequestParam String title,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String fullPermanentAddress,
                           @RequestParam String fullResidentialAddress,
                           @RequestParam String occupation,
                           @RequestParam Double totalGrossCompensation,
                           @RequestParam String aadharCardNumber,
                           @RequestParam String dateOfBirth){
        return userService.createUser(
                title,
                firstName,
                lastName,
                email,
                password,
                fullPermanentAddress,
                fullResidentialAddress,
                occupation,
                totalGrossCompensation,
                aadharCardNumber,
                dateOfBirth
        );
    }

    @PostMapping("/login")
    public void loginUser( @RequestParam String email,
                           @RequestParam String password,){
        return userService.loginUser(
                email,
                password
        );
    }
    //login

}
