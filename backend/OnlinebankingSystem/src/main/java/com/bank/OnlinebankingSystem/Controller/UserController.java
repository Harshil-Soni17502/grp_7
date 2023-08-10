package com.bank.OnlinebankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.OnlinebankingSystem.Repository.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;
	
}
