package com.lifebank.banksProducts.response;

import java.io.Serializable;
import java.util.List;

import com.lifebank.banksProducts.model.domain.Transaction;

@SuppressWarnings("serial")
public class CreditAccountResponse implements Serializable {

	private String id;
	private String startDate;
	private String endDate;
	private double limit;
	private double available;
	private double interestRate;
	private double interestAmount;
	private int monthlyCut;
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
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	public double getAvailable() {
		return available;
	}
	public void setAvailable(double available) {
		this.available = available;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}
	public int getMonthlyCut() {
		return monthlyCut;
	}
	public void setMonthlyCut(int monthlyCut) {
		this.monthlyCut = monthlyCut;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
