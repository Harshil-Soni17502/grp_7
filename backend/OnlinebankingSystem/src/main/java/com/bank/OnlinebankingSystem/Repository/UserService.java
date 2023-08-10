package com.bank.OnlinebankingSystem.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Service.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userdao;
}
