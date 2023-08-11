package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    //createAccount
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestParam String transactionPassword,
                                                @RequestParam Long userId,
                                                @RequestParam String accountType){
        return accountService.createAccount(transactionPassword, userId, accountType);

    }

    //displayAccount
    @GetMapping
    public ResponseEntity<AccountSummaryDTO> displayAccount(@RequestParam String accountNumber,
                                                            @RequestParam String transactionPassword){
        return accountService.displayAccount(accountNumber,transactionPassword);
    }

}