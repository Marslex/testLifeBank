package com.lifebank.manageBeneficiary.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@Entity
@Table(name="loan_account", schema = "acc_lifebank")
@PrimaryKeyJoinColumn(referencedColumnName="id_account")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanAccount extends Account implements Serializable {
	
	@Column(name = "total", precision=9, scale=2)
	private BigDecimal total;
	
	@Column(name = "debt", precision=9, scale=2)
	private BigDecimal debt;
	
	@Column(name = "interest_rate", precision=9, scale=2)
	private BigDecimal interestRate;
	
	@Column(name = "interest_amount", precision=9, scale=2)
	private BigDecimal interestAmount;
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDebt() {
		return debt;
	}
	public void setDebt(BigDecimal debt) {
		this.debt = debt;
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
	
	
}
