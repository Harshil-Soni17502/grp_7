package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userdao;

	public ResponseEntity<String> createUser(String title, String firstName, String lastName, String email, String password,
											 String fullPermanentAddress, String fullResidentialAddress, String occupation,
											 Double totalGrossCompensation, String aadharCardNumber, String dateOfBirth,
											 String mobileNumber) {
		try {
			User user = new User();
			user.setAadharNumber(aadharCardNumber);
			user.setDOB(dateOfBirth);
			user.setEmailId(email);
			user.setResidentialAddress(fullResidentialAddress);
			user.setPermanentAddress(fullPermanentAddress);
			user.setFirstName(firstName);
			user.setOccupation(occupation);
			user.setSalutation(title);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setMobileNumber(mobileNumber);
			user.setApproved(false);
			user.setTotalGrossIncome(totalGrossCompensation);
			System.out.println(aadharCardNumber);
			System.out.println(dateOfBirth);
			System.out.println(email);
			System.out.println(fullResidentialAddress);
			System.out.println(fullPermanentAddress);
			System.out.println(firstName);
			System.out.println(occupation);
			System.out.println(title);
			System.out.println(lastName);
			System.out.println(password);
			System.out.println(mobileNumber);
			userdao.save(user);
			return ResponseEntity.ok("User Created");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(200).body(e.getMessage());
		}


	}

	public ResponseEntity<String> loginUser(String email, String password) {
		try {
			User user = new User();
			user.setPassword(password);
			user.setEmailId(email);
			if(!userdao.findByEmailIdAndPassword(email,password).isEmpty()){
				System.out.println("valid");
				return ResponseEntity.ok("VALID");
			}
			else{
				System.out.println("invalid");
				return ResponseEntity.ok("INVALID");
			}
		}
		catch (Exception e){
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
}
