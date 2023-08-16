package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.OnlinebankingSystem.Repository.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserDao userdao;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
			user.setPassword(passwordEncoder.encode(password));
			user.setMobileNumber(mobileNumber);
			user.setTotalGrossIncome(totalGrossCompensation);

			userdao.save(user);
			return ResponseEntity.ok("User Created");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(200).body(e.getMessage());
		}


	}

	public ResponseEntity<User> loginUser(String email, String password) {
		try {
			List<User> userList = userdao.findByEmailIdAndPassword(email,password);
			if(!userList.isEmpty()){
				System.out.println("valid");
				return ResponseEntity.ok(userList.get(0));
			}
			else{
				System.out.println("invalid");
				return ResponseEntity.ok(null);
			}
		}
		catch (Exception e){
			return ResponseEntity.status(500).body(null);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user =  userdao.findByEmailId(email);

		return new org.springframework.security.core.userdetails.User(user.getEmailId(),user.getPassword(),new ArrayList<>());
		//return new org.springframework.security.core.userdetails.User("admin","pwd",new ArrayList<>());
	}


}
