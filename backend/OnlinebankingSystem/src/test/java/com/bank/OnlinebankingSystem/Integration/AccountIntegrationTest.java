package com.bank.OnlinebankingSystem.Integration;

import com.bank.OnlinebankingSystem.Controller.AccountController;
import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Service.*;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AccountController.class)
public class AccountIntegrationTest {

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
    private TransactionService transactionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateAccount() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transactionPassword", "password123");
        payload.put("userId", 123L);
        payload.put("accountType", "Savings");
        payload.put("openingBalance", 1000);

        when(accountService.createAccount(anyString(),anyLong(),anyString(),anyInt())).thenReturn(ResponseEntity.ok(""));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testDisplayAccount() throws Exception {
        String accountNumber = "123";
        User user = new User(123L,"Miss","Tess","Cameron","t@gmail.com","pass","9090909090","123123123123","address","address","11/12/2000","bizness",22200.00);

        when(accountService.displayAccount(anyLong())).thenReturn(ResponseEntity.ok(new AccountSummaryDTO()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/display")
                        .param("accountNumber", accountNumber))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

    }
}
