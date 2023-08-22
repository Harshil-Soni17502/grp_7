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
public class AccountDetailsResponse {
	private String accountType;
	private String balance;
	private String userEmail;
	private Boolean isApproved;
	private List<Transaction> transactions;
}
