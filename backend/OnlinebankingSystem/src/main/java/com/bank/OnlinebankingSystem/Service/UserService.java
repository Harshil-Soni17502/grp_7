package com.bank.OnlinebankingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userdao;
}
