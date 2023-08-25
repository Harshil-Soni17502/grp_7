package com.bank.OnlinebankingSystem;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;



// import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
import com.bank.OnlinebankingSystem.Service.AccountService;
import com.bank.OnlinebankingSystem.Service.UserService;
import com.bank.OnlinebankingSystem.Service.TransactionService;

import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;

import com.bank.OnlinebankingSystem.utility.JWTUtility;

import com.bank.OnlinebankingSystem.Entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AdminService adminService;
	
	@MockBean
	private TransactionService transactionService;
	
	@MockBean
	private JWTUtility jwtUtility;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private BeneficiaryService beneficiaryService;
	
	@MockBean
	private AccountService accountService;
	

	@MockBean
	private AccountDao accountDao;
	@MockBean
	private UserDao userDao;
	@MockBean
	private TransactionDao transactionDao;
	@MockBean
	private BeneficiaryDao beneficiaryDao;
	
	ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void createUsersTest() throws MalformedRequestException, Exception {
		User user = new User();
        user.setAadharNumber("123456789123");
        user.setDOB("17052002");
        user.setEmailId("h@gmail.com");
        user.setFirstName("HNS");
        user.setId(1L);
        user.setLastName("Soni");
        user.setMobileNumber("1234567890");
        user.setOccupation("Business");
        user.setPassword("hns");
        user.setPermanentAddress("address");
        user.setResidentialAddress("address");
        user.setSalutation("Mr.");
        user.setTotalGrossIncome(0.00);
        
        Mockito.when(userService.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(ResponseEntity.ok(user.toString()));
		assertThat(ResponseEntity.ok(user.toString()),equalTo(userService.createUser("Mr.", "HNS", "Soni", "h@gmail.com", "hns", "address", "address", "Business", 0.00, "123456789123", "17052002", "1234567890")));
	}
	
	@Test
    void loginUser_ValidCredentials_Success() throws MalformedRequestException, Exception {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.get(0).setEmailId("valid@email.com");
        userList.get(0).setPassword("validPassword");
        userList.get(0).setAadharNumber("123456789123");
        userList.get(0).setDOB("17052002");
        userList.get(0).setFirstName("HNS");
        userList.get(0).setId(1L);
        userList.get(0).setLastName("Soni");
        userList.get(0).setMobileNumber("1234567890");
        userList.get(0).setOccupation("Business");
        userList.get(0).setPermanentAddress("address");
        userList.get(0).setResidentialAddress("address");
        userList.get(0).setSalutation("Mr.");
        userList.get(0).setTotalGrossIncome(0.00);
        
		Mockito.when(userService.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(ResponseEntity.ok(userList.get(0)));
		assertThat(ResponseEntity.ok(userList.get(0)),equalTo(userService.loginUser("valid@email.com", "validPassword")));
    }
	
//	@Test
//    void loadUserByUsername_ValidUser_Success() {
//        // Arrange
//        User user = new User();
//        user.setAadharNumber("123456789123");
//        user.setDOB("17052002");
//        user.setEmailId("valid@email.com");
//        user.setFirstName("HNS");
//        user.setId(1L);
//        user.setLastName("Soni");
//        user.setMobileNumber("1234567890");
//        user.setOccupation("Business");
//        user.setPassword("hns");
//        user.setPermanentAddress("address");
//        user.setResidentialAddress("address");
//        user.setSalutation("Mr.");
//        user.setTotalGrossIncome(0.00);
//
//        
//        // Act
//        UserDetails userDetails = userService.loadUserByUsername("valid@email.com");
//        
////        Mockito.when(userService.loadUserByUsername(Mockito.anyString())).thenReturn((UserDetails) ResponseEntity.ok(userService.loadUserByUsername("valid@email.com")));
////		assertThat(ResponseEntity.ok(userService.loadUserByUsername("valid@email.com")),equalTo(userService.loadUserByUsername("valid@email.com")));
//        
//        assertNotNull(userDetails);
//        assertEquals(user.getPassword(), userDetails.getPassword());
//    }
}
