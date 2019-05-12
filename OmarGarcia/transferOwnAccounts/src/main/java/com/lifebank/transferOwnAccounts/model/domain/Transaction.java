package com.lifebank.transferOwnAccounts.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@Entity
@Table(name="transaction", schema = "txn_lifebank")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","account"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable {
	
	@Id
	@Column(name = "transaction_number", length = 18, unique = true)//YYYYMMDDHHMMSSSSS
	private String transactionNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "amount", precision=9, scale=2)
	private Double amount;
	
	@Column(name = "transaction_type", length = 1)
	private String transactionType;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_account")
	private Account account;

	public Transaction(){}
	
	public Transaction(/*Integer idTransaction, */Date transactionDate, String description, Double amount,
			Account account,String transactionType,String transactionNumber) {
		super();
		//this.idTransaction = idTransaction;
		this.transactionDate = transactionDate;
		this.description = description;
		this.amount = amount;
		this.account = account;
		this.transactionType = transactionType;
		this.transactionNumber = transactionNumber;
	}

	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
}
