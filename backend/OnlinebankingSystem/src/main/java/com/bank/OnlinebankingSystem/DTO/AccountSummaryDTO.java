package com.bank.OnlinebankingSystem.DTO;

import com.bank.OnlinebankingSystem.Entity.Transaction;

import java.util.ArrayList;
import java.util.List;


public class AccountSummaryDTO {
	private Long accountNumber;
	private Integer balance;
	private String accountType;
	private List<Transaction> transactionHistory = new ArrayList<>();

	public Long getAccountNumber() {
		return accountNumber;
	}

	public Integer getBalance() {
		return balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactionHistory() {
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

	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	@Override
	public String toString() {
		return "AccountSummaryDTO{" +
				"accountNumber=" + accountNumber +
				", balance=" + balance +
				", accountType='" + accountType + '\'' +
				", transactionHistory=" + transactionHistory +
				'}';
	}
}

	