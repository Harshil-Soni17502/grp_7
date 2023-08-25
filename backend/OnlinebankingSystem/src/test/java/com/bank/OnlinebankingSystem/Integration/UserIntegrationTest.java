package com.bank.OnlinebankingSystem.Integration;

import com.bank.OnlinebankingSystem.Controller.UserController;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Entity.JwtRequest;
import com.bank.OnlinebankingSystem.Entity.JwtResponse;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.Service.*;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

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

    @MockBean
    private TransactionService transactionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLoginUser() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("user@example.com", "password123");

        User user = new User();
        user.setId(123L);
        user.setFirstName("Test");
        user.setLastName("User");
        User user2 = new User(123L,"Miss","Tess","Cameron","t@gmail.com","pass","9090909090","123123123123","address","address","11/12/2000","bizness",22200.00);

        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setId(456L);
        accounts.add(account);

        Map<Long, List<Beneficiary>> accountBeneficiaryMap = new HashMap<>();
        List<Beneficiary> beneficiaries = new ArrayList<>();
        accountBeneficiaryMap.put(account.getId(), beneficiaries);



        String token = "generated-jwt-token";

        when(userService.loginUser(anyString(),anyString())).thenReturn(ResponseEntity.ok(user2));
        when(userService.loadUserByUsername(anyString())).thenReturn(null);
        when(jwtUtility.generateToken(null)).thenReturn(null);
        when(jwtUtility.getExpirationDateFromToken(null)).thenReturn(null);
        when(accountService.findByUserId(null)).thenReturn(null);
        when(beneficiaryService.getBeneficiariesOf(null)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

    }

    @Test
    public void testCreateUser() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Mr.");
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("email", "john@example.com");
        payload.put("password", "password123");
        payload.put("fullPermanentAddress", "123 Main St");
        payload.put("fullResidentialAddress", "456 Elm St");
        payload.put("occupation", "Software Engineer");
        payload.put("totalGrossCompensation", 80000.0);
        payload.put("aadharCardNumber", "123456789012");
        payload.put("dateOfBirth", "1990-01-01");
        payload.put("mobileNumber", "1234567890");

        when(userService.createUser(anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyDouble(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(ResponseEntity.ok(""));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
