package com.bank.OnlinebankingSystem.Integration;

import com.bank.OnlinebankingSystem.Controller.TransactionController;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.Service.*;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.TransactionFailedToLogException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(TransactionController.class)
@ExtendWith(SpringExtension.class)
public class TransactionIntegrationTest {

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
    //@WithUserDetails("testuser")
    public void testMakeTransaction() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("fromAccountNo", 123L);
        payload.put("toAccountNo", 456L);
        payload.put("transactionType", "transfer");
        payload.put("amount", 100);
        payload.put("password", "password123");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/make")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    //@WithUserDetails("testuser")
    public void testGetTransactionsBetween() throws Exception {
        String accountNo = "123";
        String t1 = "2023-01-01";
        String t2 = "2023-08-24";

        List<Transaction> mockTransactions = Collections.singletonList(new Transaction(/*...*/));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/transaction/getTransactionsBetweenFor")
                        .param("t1", t1)
                        .param("t2", t2)
                        .param("accountNo", accountNo))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}




