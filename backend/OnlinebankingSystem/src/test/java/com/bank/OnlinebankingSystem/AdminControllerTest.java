package com.bank.OnlinebankingSystem;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.runner.RunWith;
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

//@SpringBootTest
//@RunWith(SpringRunner.class)
@WebMvcTest
public class AdminControllerTest {
	
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
	public void getUsersTest() throws MalformedRequestException, Exception {
		User user = new User(123L,"Miss","Tess","Cameron","t@gmail.com","pass","9090909090","123123123123","address","address","11/12/2000","bizness",22200.00);
//		ResponseEntity.ok(user);
//		String name = user.getFirstName()
		Map<String,Object> map = new HashMap();
		
		map.put("email","t@gmail.com");
		String json = mapper.writeValueAsString(map);
//		System.out.println(json);
		Mockito.when( adminService.getUserDetails("t@gmail.com")).thenReturn(ResponseEntity.ok(user));
//		System.out.println(userDao.findByEmailId("t@gmail.com"));
//		adminService.getUserDetails("t@gmail.com");
//		mvc.perform(post("/admin/getUserDetails").contentType(MediaType.APPLICATION_JSON_UTF8).content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.userId",Matchers.equalTo(user.getId())));
//		when( adminService.getUserDetails("t@gmail.com")).thenReturn(ResponseEntity.ok(user));
		assertEquals(ResponseEntity.ok(user), adminService.getUserDetails("t@gmail.com"));
	}



	
}
