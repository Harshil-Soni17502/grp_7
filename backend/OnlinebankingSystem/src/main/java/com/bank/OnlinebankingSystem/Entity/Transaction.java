package com.bank.OnlinebankingSystem.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Transaction")
public class Transaction {

	@NotNull
	private Integer amount;
	@NotNull
	private String transactionType;

	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotNull
	private Timestamp transactionTimestamp;

	@NotNull
	private Long fromAccountNo;

	@NotNull
	private Long toAccountNo;

	 public Transaction() {}

	@Column(name = "amount", nullable = false, unique=false, insertable=true, updatable=true)
	public Integer getAmount() { return amount; }

	 public void setAmount(Integer amount) { this.amount = amount; }

	@Column(name = "transaction_type", nullable = false, unique=false, insertable=true, updatable=true)
	public String getTransactionType() { return transactionType; }

	 public void setTransactionType(String transactionType) { this.transactionType
	 = transactionType; }

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO) public Long getId() { return
	 id; }

	 public void setId(Long id) { this.id = id; }

	@Column(name = "transaction_date", nullable = false, unique=false, insertable=true, updatable=true)
	public Timestamp getTransactionTimestamp() { return transactionTimestamp; }

	 public void setTransactionTimestamp(Timestamp transactionTimestamp) { this.transactionTimestamp =
			 transactionTimestamp; }

	@Column(name = "from_account", nullable = false, unique=false, insertable=true, updatable=true)

	public Long getFromAccountNo() { return fromAccountNo; }

	 public void setFromAccountNo(Long fromAccountNo) { this.fromAccountNo =
	 fromAccountNo; }

	@Column(name = "to_account", nullable = false, unique=false, insertable=true, updatable=true)
	public Long getToAccountNo() { return toAccountNo; }

	 public void setToAccountNo(Long toAccountNo) { this.toAccountNo = toAccountNo; }

	
	
	
	
	
}
