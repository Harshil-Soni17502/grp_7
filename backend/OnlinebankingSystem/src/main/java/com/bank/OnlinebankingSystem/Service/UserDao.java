package com.bank.OnlinebankingSystem.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import Model.User;

public interface UserDao extends JpaRepository<User,Integer>{
	
}
