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
public class pendingAccountDetailsResponse {
	private Long id;
	private String accountType;
	private Integer openingBalance;
	private String Name;
	private String Email;
	private String isApproved;
}
