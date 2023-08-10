package com.bank.OnlinebankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.OnlinebankingSystem.Repository.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;
	
}
