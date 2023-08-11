package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDao accountdao;

    public ResponseEntity<String> createAccount(String transactionPassword, Long userId, String accountType) {
        return ResponseEntity.ok(accountdao.findAll().toString());
    }

    public ResponseEntity<AccountSummaryDTO> displayAccount(String accountNumber, String transactionPassword) {
        return ResponseEntity.ok(new AccountSummaryDTO());
    }
}

