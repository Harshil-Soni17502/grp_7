package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    //1. insert transaction -> update balance in both accounts
    @PostMapping("/make")
    @CrossOrigin(origins ="http://localhost:3000")

    public ResponseEntity<String> makeTransaction(@RequestBody Map<String,Object> payload)throws MalformedRequestException, Exception{
        System.out.println(payload.get("fromAccountNo"));
        System.out.println(payload.get("toAccountNo"));
        System.out.println(payload.get("transactionType"));
        return transactionService.makeTransaction(
               Long.valueOf(payload.get("fromAccountNo").toString()),
               Long.valueOf(payload.get("toAccountNo").toString()),
                payload.get("transactionType").toString(),
                Integer.valueOf(payload.get("amount").toString())
        );
    }

    //2. retrieve transactions for account between date to date
    @GetMapping("/getTransactionsBetweenFor")
    @CrossOrigin(origins ="http://localhost:3000")

    public ResponseEntity<List<Transaction>> getTransactionsBetween(
            @RequestParam String t1,
            @RequestParam String t2,
            @RequestParam String accountNo)throws MalformedRequestException, Exception{
       return transactionService.getTransactionsBetween(
               Long.valueOf(accountNo),
               Timestamp.valueOf(t1),
               Timestamp.valueOf(t2));

    }



}
