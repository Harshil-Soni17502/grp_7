package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.DTO.AccountSummaryDTO;
import com.bank.OnlinebankingSystem.Service.AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;
    
    //createAccount
    @PostMapping("/create")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> createAccount(@RequestBody Map<String,Object> payload){
        return accountService.createAccount(payload.get("transactionPassword").toString() ,Long.getLong(payload.get("userId").toString()), payload.get("accountType").toString());

    }

    //displayAccount
    @GetMapping("/display")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<AccountSummaryDTO> displayAccount(@RequestParam Long accountNumber
                                                           ){
        return accountService.displayAccount(accountNumber);
    }

}