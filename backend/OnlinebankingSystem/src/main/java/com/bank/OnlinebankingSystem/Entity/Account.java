package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {
	private String transactionPassword;
	private String accountType;
	private Integer balance;
	private Long id;
    private User user;
    private Boolean isApproved;

    public Account() {
    }

    public Account(String transactionPassword, String accountType, Integer balance, Long id, User user, Boolean isApproved) {
        this.transactionPassword = transactionPassword;
        this.accountType = accountType;
        this.balance = balance;
        this.id = id;
        this.user = user;
        this.isApproved = isApproved;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull
    @Column(name="password", nullable = false, unique=false, insertable=true, updatable=true)
    @JsonIgnore
    public String getTransactionPassword() {
        return transactionPassword;
    }
    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }
    
    @NotNull
    @Column(name="account_type", nullable=false, unique=false, insertable=true, updatable=true)
    public String getAccountType() {
    	return accountType;
    }
    public void setAccountType(String accountType) {
    	this.accountType = accountType;
    }
    
    @NotNull
    @Min(0)
    @Column(name="balance", nullable=false, unique=false, insertable=true, updatable=true)
    public Integer getBalance() {
    	return balance;
    }
    public void setBalance(Integer balance) {
    	this.balance = balance;
    }
    
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id", nullable=true)
    @JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

    @Column(name="isApproved", nullable=false, unique=false, insertable=true, updatable=true)
    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
