package com.bank.OnlinebankingSystem;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.Service.AccountService;
import com.bank.OnlinebankingSystem.Service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao accountDao;

    @Mock
    private UserDao userDao;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount() throws MalformedRequestException, Exception {
        User user = new User();
        user.setId(1L);
        Account account = new Account();
        account.setId(1L);
        account.setTransactionPassword("password");
        account.setBalance(1000);
        account.setAccountType("savings");
        account.setUser(user);
        account.setIsApproved("pending");

        when(userDao.findById(eq(1L))).thenReturn(Optional.of(user));
        when(accountDao.save(any())).thenReturn(account);

        ResponseEntity<String> response = accountService.createAccount("password", 1L, "savings", 1000);

        assertEquals(ResponseEntity.ok("OK"), response);
        verify(accountDao, times(1)).save(any());
    }

    @Test
    public void testDisplayAccount() throws MalformedRequestException, Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(1000);
        account.setAccountType("savings");

        when(accountDao.getById(eq(1L))).thenReturn(account);
        when(transactionService.getRecentTransactions(eq(1L))).thenReturn(new ArrayList<>());

        ResponseEntity<AccountSummaryDTO> response = accountService.displayAccount(1L);

        AccountSummaryDTO expectedDTO = new AccountSummaryDTO();
        expectedDTO.setBalance(1000);
        expectedDTO.setAccountNumber(1L);
        expectedDTO.setAccountType("savings");
        expectedDTO.setTransactionHistory(new ArrayList<>());

        assertEquals(ResponseEntity.ok(expectedDTO).toString(), response.toString());
        verify(accountDao, times(1)).getById(eq(1L));
        verify(transactionService, times(1)).getRecentTransactions(eq(1L));
    }

    @Test
    public void testFindByUserId() throws Exception {
        User user = new User();
        user.setId(1L);
        Account account = new Account();
        account.setId(1L);
        account.setTransactionPassword("password");
        account.setBalance(1000);
        account.setAccountType("savings");
        account.setUser(user);
        account.setIsApproved("pending");

        when(accountDao.findByUser_Id(eq(1L))).thenReturn(new ArrayList<>());

        accountService.findByUserId(1L);

        verify(accountDao, times(1)).findByUser_Id(eq(1L));
    }

    // Add more test cases as needed for different scenarios and edge cases
}

