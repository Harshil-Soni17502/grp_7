package com.bank.OnlinebankingSystem;

import com.bank.OnlinebankingSystem.Controller.AccountController;
import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Service.AccountService;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount() throws MalformedRequestException, Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transactionPassword", "password");
        payload.put("userId", 1L);
        payload.put("accountType", "savings");
        payload.put("openingBalance", 1000);

        when(accountService.createAccount(any(), any(), any(), anyInt())).thenReturn(ResponseEntity.ok("OK"));

        ResponseEntity<String> response = accountController.createAccount(payload);

        assertEquals(ResponseEntity.ok("OK"), response);
    }

    @Test
    public void testDisplayAccount() throws Exception {
        String accountNumber = "123";

        when(accountService.displayAccount(eq(123L))).thenReturn(ResponseEntity.ok(new AccountSummaryDTO()));

        ResponseEntity<AccountSummaryDTO> response = accountController.displayAccount(accountNumber);

        assertEquals(ResponseEntity.ok(new AccountSummaryDTO()).toString(), response.toString());
    }

    // Add more test cases as needed for different scenarios and edge cases
}

