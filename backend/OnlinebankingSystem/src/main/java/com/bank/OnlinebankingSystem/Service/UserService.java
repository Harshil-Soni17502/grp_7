package com.bank.OnlinebankingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userdao;

	public ResponseEntity<String> createUser(String title, String firstName, String lastName, String email, String password,
											 String fullPermanentAddress, String fullResidentialAddress, String occupation,
											 Double totalGrossCompensation, String aadharCardNumber, String dateOfBirth) {

		return ResponseEntity.ok(userdao.findAll().toString());
	}

	public ResponseEntity<String> loginUser(String email, String password) {
		return ResponseEntity.ok(userdao.findAll().toString());
	}
}
