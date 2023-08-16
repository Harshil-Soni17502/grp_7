package com.bank.OnlinebankingSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwtToken;
//    private Long userId;
//    private String userName;
//    private List<Account> account;
//    private Map<Long,List<Beneficiary>> accountBeneficiaryMap;
//    private Date timeToExpiry;

}
