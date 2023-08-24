package com.bank.OnlinebankingSystem;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Service.TransactionService;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.TransactionFailedToLogException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private AccountDao accountDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMakeTransaction() throws MalformedRequestException, TransactionFailedToLogException {
        // Create sample data
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(1000);
        fromAccount.setTransactionPassword("password");

        Account toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setBalance(2000);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(fromAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(toAccount));
        when(accountDao.getReferenceById(eq(1L))).thenReturn(fromAccount);
        when(accountDao.getReferenceById(eq(2L))).thenReturn(toAccount);
        when(transactionDao.save(any())).thenReturn(new Transaction());

        ResponseEntity<String> response = transactionService.makeTransaction(1L, 2L, "Transfer", 500, "password");

        assertEquals(ResponseEntity.ok("OK"), response);
        assertEquals(500, fromAccount.getBalance());
        assertEquals(2500, toAccount.getBalance());

        verify(accountDao, times(1)).save(fromAccount);
        verify(accountDao, times(1)).save(toAccount);
        verify(transactionDao, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testMakeTransactionInvalidPassword() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(1000);
        fromAccount.setTransactionPassword("password");

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(fromAccount));

        MalformedRequestException exception = org.junit.jupiter.api.Assertions.assertThrows(
                MalformedRequestException.class,
                () -> transactionService.makeTransaction(1L, 2L, "Transfer", 500, "wrongPassword")
        );

        assertEquals("Account does not exist", exception.getMessage());
    }

    @Test
    public void testGetTransactionsBetween() throws MalformedRequestException, Exception {
        Account account = new Account();
        account.setId(1L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(account));
        when(transactionDao.findTransactionsByAccountAndTimestamps(eq(1L), any(Timestamp.class), any(Timestamp.class)))
                .thenReturn(new ArrayList<>());

        ResponseEntity<List<Transaction>> response = transactionService.getTransactionsBetween(1L,
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        assertEquals(ResponseEntity.ok(new ArrayList<>()), response);
        verify(transactionDao, times(1)).findTransactionsByAccountAndTimestamps(eq(1L), any(Timestamp.class), any(Timestamp.class));
    }

    @Test
    public void testGetRecentTransactions() {
        Account account = new Account();
        account.setId(1L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(account));
        when(transactionDao.findTransactionsByAccount(eq(1L))).thenReturn(new ArrayList<>());

        List<Transaction> response = transactionService.getRecentTransactions(1L);

        assertEquals(new ArrayList<>(), response);
        verify(transactionDao, times(1)).findTransactionsByAccount(eq(1L));
    }

    @Test
    public void testWithdraw() throws MalformedRequestException, TransactionFailedToLogException {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(1000);
        account.setTransactionPassword("password");

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(account));
        when(accountDao.getReferenceById(eq(1L))).thenReturn(account);

        ResponseEntity<String> response = transactionService.withdraw(1L, 500, "password");

        assertEquals(ResponseEntity.ok("OK"), response);
        assertEquals(500, account.getBalance());
        verify(accountDao, times(1)).save(account);
    }
}

