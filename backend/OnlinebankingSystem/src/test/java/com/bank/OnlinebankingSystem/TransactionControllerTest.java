package com.bank.OnlinebankingSystem;


import com.bank.OnlinebankingSystem.Entity.Transaction;
import com.bank.OnlinebankingSystem.Entity.User;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import com.bank.OnlinebankingSystem.Repository.TransactionDao;
import com.bank.OnlinebankingSystem.Repository.UserDao;
import com.bank.OnlinebankingSystem.Service.*;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.TransactionFailedToLogException;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

//public ResponseEntity<String> makeTransaction(@RequestBody Map<String,Object> payload)throws MalformedRequestException, TransactionFailedToLogException{
//        System.out.println(payload.get("fromAccountNo"));
//        System.out.println(payload.get("toAccountNo"));
//        System.out.println(payload.get("transactionType"));
//        return transactionService.makeTransaction(
//        Long.valueOf(payload.get("fromAccountNo").toString()),
//        Long.valueOf(payload.get("toAccountNo").toString()),
//        payload.get("transactionType").toString(),
//        Integer.valueOf(payload.get("amount").toString()),
//        payload.get("password").toString()
//        );
//        }
//
//@PostMapping("/withdraw")
//@CrossOrigin(origins ="http://localhost:3000")
//public ResponseEntity<String> withdraw(@RequestBody Map<String,Object> payload)throws MalformedRequestException, TransactionFailedToLogException{
////        System.out.println(payload.get("fromAccountNo"));
////        System.out.println(payload.get("toAccountNo"));
////        System.out.println(payload.get("transactionType"));
////        return transactionService.makeTransaction(
////                Long.valueOf(payload.get("fromAccountNo").toString()),
////                Long.valueOf(payload.get("toAccountNo").toString()),
////                payload.get("transactionType").toString(),
////                Integer.valueOf(payload.get("amount").toString())
////        );
//        return transactionService.withdraw(Long.valueOf(payload.get("fromAccountNo").toString()),
//        Integer.valueOf(payload.get("amount").toString()),
//        payload.get("password").toString()
//        );
//
//        }
//
////2. retrieve transactions for account between date to date
//@GetMapping("/getTransactionsBetweenFor")
//@CrossOrigin(origins ="http://localhost:3000")
//
//public ResponseEntity<List<Transaction>> getTransactionsBetween(
//@RequestParam String t1,
//@RequestParam String t2,
//@RequestParam String accountNo)throws MalformedRequestException, Exception{
//        System.out.println(t1);
//        System.out.println(t2);
//        return transactionService.getTransactionsBetween(
//        Long.valueOf(accountNo),
//        Timestamp.valueOf(t1 + " 00:00:00"),
//        Timestamp.valueOf(t2+ " 23:59:59"));
//
//        }


@WebMvcTest
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdminService adminService;

    @MockBean
    private TransactionService transactionService;

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



    @Test
    public void makeTransactionTest() throws MalformedRequestException, TransactionFailedToLogException {
        Transaction t = new Transaction();
        t.setTransactionType("NEFT");
        t.setTransactionTimestamp(Timestamp.valueOf("2023-10-10" + " 00:00:00"));
        t.setAmount(100);
        t.setId(123L);
        t.setFromAccount(null);
        t.setToAccount(null);


        Mockito.when(transactionService.makeTransaction(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(t.toString()));
        assertThat(ResponseEntity.ok(t.toString()),equalTo(transactionService.makeTransaction(1L,2L,"",0,"")));

    }

}

//    @Test
//    public void getUsersTest() throws MalformedRequestException, Exception {
//        User user = new User(123L,"Miss","Tess","Cameron","t@gmail.com","pass","9090909090","123123123123","address","address","11/12/2000","bizness",22200.00);

//        Map<String,Object> map = new HashMap();
//
//        map.put("email","t@gmail.com");
//        String json = mapper.writeValueAsString(map);
//        Mockito.when( adminService.getUserDetails("t@gmail.com")).thenReturn(ResponseEntity.ok(user));

//        assertEquals(ResponseEntity.ok(user), adminService.getUserDetails("t@gmail.com"));
//    }
