package com.bank.OnlinebankingSystem.DTO;

import java.util.ArrayList;
import java.util.List;


public class AccountSummaryDTO {
	private Long accountNumber;
	private Integer balance;
	private String accountType;
	private List<String> transactionHistory = new ArrayList<String>();

	public Long getAccountNumber() {
		return accountNumber;
	}

	public Integer getBalance() {
		return balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public List<String> getTransactionHistory() {
		return transactionHistory;
	}

	public AccountSummaryDTO() {}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setTransactionHistory(List<String> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
}

	