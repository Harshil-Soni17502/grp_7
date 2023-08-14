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

@Entity
@Table(name = "account")
public class Account {
	private String transactionPassword;
	private String accountType;
	private Integer balance;
	private Long id;
    private User user;
    private List<Transaction> fromAccountTransactions;
    private List<Transaction> toAccountTransactions;
    private List<Beneficiary> beneficiaryAccounts;

    public Account() {
    }

    public Account(String transactionPassword, String accountType, Integer balance, Long id) {
        this.transactionPassword = transactionPassword;
        this.accountType = accountType;
        this.balance = balance;
        this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="fromAccount")
	public List<Transaction> getFromAccountTransactions() {
		return fromAccountTransactions;
	}
	public void setFromAccountTransactions(List<Transaction> fromAccountTransactions) {
		this.fromAccountTransactions = fromAccountTransactions;
	}
	
	@OneToMany(mappedBy="toAccount")
	public List<Transaction> getToAccountTransactions() {
		return toAccountTransactions;
	}
	public void setToAccountTransactions(List<Transaction> toAccountTransactions) {
		this.toAccountTransactions = toAccountTransactions;
	}
	
	@OneToMany(mappedBy="beneficiaryAccount")
	public List<Beneficiary> getBeneficiaryAccounts() {
		return beneficiaryAccounts;
	}

	public void setBeneficiaryAccounts(List<Beneficiary> beneficiaryAccounts) {
		this.beneficiaryAccounts = beneficiaryAccounts;
	}
}
