package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Account")
public class Account {
	@NotNull
    private String transactionPassword;
	@NotNull
    private String accountType;
	@NotNull
	@Min(0)
    private Integer balance;
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long userId;

    public Account() {
    }

    public Account(String transactionPassword, String accountType, Integer balance, Long id) {
        this.transactionPassword = transactionPassword;
        this.accountType = accountType;
        this.balance = balance;
        this.id = id;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="password", nullable = false, unique=false, insertable=true, updatable=true)
    public String getTransactionPassword() {
        return transactionPassword;
    }
    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }
    
    @Column(name="account_type", nullable=false, unique=false, insertable=true, updatable=true)
    public String getAccountType() {
    	return accountType;
    }
    public void setAccountType(String accountType) {
    	this.accountType = accountType;
    }
    
    @Column(name="balance", nullable=false, unique=false, insertable=true, updatable=true)
    public Integer getBalance() {
    	return balance;
    }
    public void setBalance(Integer balance) {
    	this.balance = balance;
    }
}
