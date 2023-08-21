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
public class UserDetailsResponse {
    private Long userId;
    private String userName;
    private String dateOfBirth;
    private String email;
    private String mobileNumber;
    private String permanentAddress;
    private String residentialAddress;
    private String occupation;
    private double totalGrossIncome;
    private String aadharNumber;
    private List<Account> account;
    private Map<Long,List<Beneficiary>> accountBeneficiaryMap;

}
