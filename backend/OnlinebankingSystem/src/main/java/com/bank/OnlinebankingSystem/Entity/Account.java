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
import javax.validation.constraints.Size;

import org.w3c.dom.Text;

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
    @Size(max = 200)
    private String isApproved;

    public Account() {
    }

    public Account(String transactionPassword, String accountType, Integer balance, Long id, User user, String isApproved) {
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

    @Column(name="is_approved", nullable=false, unique=false, insertable=true, updatable=true)
    @NotNull
    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String approved) {
        this.isApproved = approved;
    }
}
