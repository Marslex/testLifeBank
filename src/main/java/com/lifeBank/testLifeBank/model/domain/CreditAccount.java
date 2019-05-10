package com.lifeBank.testLifeBank.model.domain;

public class CreditAccount extends Account {

	private Double limit;
	
	private Double available;
	
	private Double interestRate;
	
	private Double interestAmount;
	
	private Integer monthlyCut;
	
	public Double getLimit() {
		return limit;
	}
	
	public void setLimit(Double limit) {
		this.limit = limit;
	}
	public Double getAvailable() {
		return available;
	}
	public void setAvailable(Double available) {
		this.available = available;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public Double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(Double interestAmount) {
		this.interestAmount = interestAmount;
	}
	public Integer getMonthlyCut() {
		return monthlyCut;
	}
	public void setMonthlyCut(Integer monthlyCut) {
		this.monthlyCut = monthlyCut;
	}
	
}
