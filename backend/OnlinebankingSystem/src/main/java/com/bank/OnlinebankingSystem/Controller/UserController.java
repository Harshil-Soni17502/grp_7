package com.bank.OnlinebankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.OnlinebankingSystem.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;

    //createUser
    //login

}
