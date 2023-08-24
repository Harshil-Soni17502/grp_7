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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Transaction that = (Transaction) o;

		if (!amount.equals(that.amount)) return false;
		if (!transactionType.equals(that.transactionType)) return false;
		if (!id.equals(that.id)) return false;
		if (!transactionTimestamp.equals(that.transactionTimestamp)) return false;
		if (fromAccount != null ? !fromAccount.equals(that.fromAccount) : that.fromAccount != null) return false;
		return toAccount != null ? toAccount.equals(that.toAccount) : that.toAccount == null;
	}

	@Override
	public int hashCode() {
		int result = amount.hashCode();
		result = 31 * result + transactionType.hashCode();
		result = 31 * result + id.hashCode();
		result = 31 * result + transactionTimestamp.hashCode();
		result = 31 * result + (fromAccount != null ? fromAccount.hashCode() : 0);
		result = 31 * result + (toAccount != null ? toAccount.hashCode() : 0);
		return result;
	}
}
