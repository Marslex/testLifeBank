package com.lifebank.banksProducts.response;

import java.io.Serializable;
import java.util.List;

import com.lifebank.banksProducts.model.domain.Transaction;

@SuppressWarnings("serial")
public class PersonalAccountResponse implements Serializable {
	private String id;
	private String startDate;
	private String endDate;
	private List<Transaction> transactions;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
