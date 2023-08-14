package com.bank.OnlinebankingSystem.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@Entity
@Table(name="transaction")
public class Transaction {

	private Integer amount;
	private String transactionType;
	private Long id;
	private Timestamp transactionTimestamp;
	private Account fromAccount;
	private Account toAccount;

	 public Transaction() {}

	 @NotNull
	 @Column(name = "amount", nullable = false, unique=false, insertable=true, updatable=true)
	public Integer getAmount() { return amount; }

	 public void setAmount(Integer amount) { this.amount = amount; }

	 @NotNull 
	@Column(name = "transaction_type", nullable = false, unique=false, insertable=true, updatable=true)
	public String getTransactionType() { return transactionType; }

	 public void setTransactionType(String transactionType) { this.transactionType
	 = transactionType; }

	 @Id
	 @NotNull
	 @GeneratedValue(strategy = GenerationType.AUTO) public Long getId() { return
	 id; }

	 public void setId(Long id) { this.id = id; }

	 @NotNull
	@Column(name = "transaction_date", nullable = false, unique=false, insertable=true, updatable=true)
	public Timestamp getTransactionTimestamp() { return transactionTimestamp; }

	 public void setTransactionTimestamp(Timestamp transactionTimestamp) { this.transactionTimestamp =
			 transactionTimestamp; }

	@ManyToOne
	@JoinColumn(name="from_account", referencedColumnName = "id", nullable=false)
	@JsonIncludeProperties({"id"})
	public Account getFromAccount() { return fromAccount; }

	 public void setFromAccount(Account fromAccount) { this.fromAccount =
	 fromAccount; }
	 
	 @ManyToOne
	@JoinColumn(name="to_account", referencedColumnName = "id", nullable=false)
	 @JsonIncludeProperties({"id"})
	public Account getToAccount() { return toAccount; }

	 public void setToAccount(Account toAccount) { this.toAccount = toAccount; }

}
