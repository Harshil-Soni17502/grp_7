package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
    private String transactionPassword;
    private String accountType;
    private Integer balance;
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Account(String transactionPassword, String accountType, Integer balance, Long id) {
        this.transactionPassword = transactionPassword;
        this.accountType = accountType;
        this.balance = balance;
        this.id = id;
    }
}
