package com.bank.OnlinebankingSystem.Integration;
import com.bank.OnlinebankingSystem.Controller.BeneficiaryController;
import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Service.*;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BeneficiaryController.class)
@ExtendWith(SpringExtension.class)
public class BeneficiaryIntegrationTest {

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
    public void testInsertBeneficiary() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("beneficiaryAccountNo", 123L);
        payload.put("associatedAccountNo", 456L);
        payload.put("beneficiaryName", "Test Beneficiary");

        when(beneficiaryService.insertBeneficiary(anyLong(),anyLong(),anyString())).thenReturn(ResponseEntity.ok("Ok"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/beneficiary/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetBeneficiariesOf() throws Exception {
        String associatedAccountNo = "123";

        when(beneficiaryService.getBeneficiariesOf(anyLong())).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/beneficiary/get")
                        .param("associatedAccountNo", associatedAccountNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

    }


}
