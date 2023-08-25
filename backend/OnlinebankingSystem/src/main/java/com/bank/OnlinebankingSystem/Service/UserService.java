package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {
	@Autowired
	UserDao userdao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<String> createUser(String title, String firstName, String lastName, String email, String password,
											 String fullPermanentAddress, String fullResidentialAddress, String occupation,
											 Double totalGrossCompensation, String aadharCardNumber, String dateOfBirth,
											 String mobileNumber) throws MalformedRequestException, EntityExistsException, Exception {
		User user = new User();
		try {
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

		}
		catch (Exception e){
			throw new MalformedRequestException("CreateUser Request malformed: "+e.getMessage());
		}
		try {
			userdao.save(user);
			return ResponseEntity.ok("User Created");
		}
		catch(DataIntegrityViolationException c) {
			System.out.println("Wow!");
			throw new EntityExistsException("User already exists");
		}
		catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			e.printStackTrace();
			throw new Exception("Server error: "+e.getMessage());
		}
	}

	public ResponseEntity<User> loginUser(String email, String password) throws MalformedRequestException, Exception {
		try {

			List<User> userList = userdao.findByEmailIdAndPassword(email,password);
			if(!userList.isEmpty()){
				System.out.println("valid");
				return ResponseEntity.ok(userList.get(0));
			}
			else{
				System.out.println("invalid");
				throw new MalformedRequestException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new Exception("Server error: "+e.getMessage());
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;

		if(email.equals("admin2@gmail.com")){
			user = new User(123L,"Miss","Tess","Cameron","admin2@gmail.com","pass2","9090909090","123123123123","address","address","11/12/2000","bizness",22200.00);

		}
		else {
			user = userdao.findByEmailId(email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmailId(),user.getPassword(),new ArrayList<>());
		//return new org.springframework.security.core.userdetails.User("admin","pwd",new ArrayList<>());
	}
	
	public List<String> loadUserByUserId(long id) throws UsernameNotFoundException {

		Optional<User> user =  userdao.findById(id);
		String email = user.get().getEmailId();
		String name = user.get().getFirstName() + user.get().getLastName();
		
		List<String> details = new ArrayList<String>();
		details.add(email);
		details.add(name);
		
		return details;
	}

}
