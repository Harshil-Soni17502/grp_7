package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	Integer amount;
	String transactionType;
	
}
