package com.bank.OnlinebankingSystem.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Model.User;


@Repository
public interface UserDao extends JpaRepository<User,Integer>{
	
}
