package com.lifebank.transferOwnAccounts.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@Entity
@Table(name="credit_account", schema = "acc_lifebank")
@PrimaryKeyJoinColumn(referencedColumnName="id_account")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditAccount extends Account implements Serializable {

	@Column(name = "acc_limit", precision=9, scale=2)
	private BigDecimal limit;
	
	@Column(name = "available", precision=9, scale=2)
	private BigDecimal available;
	
	@Column(name = "interest_rate", precision=9, scale=4)
	private BigDecimal interestRate;
	
	@Column(name = "interest_amount", precision=9, scale=4)
	private BigDecimal interestAmount;
	
	@Column(name = "monthly_cut")
	private Integer monthlyCut;

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public Integer getMonthlyCut() {
		return monthlyCut;
	}

	public void setMonthlyCut(Integer monthlyCut) {
		this.monthlyCut = monthlyCut;
	}
	
}
