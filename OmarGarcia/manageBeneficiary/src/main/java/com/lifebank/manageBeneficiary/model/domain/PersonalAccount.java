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
@Table(name="personal_account", schema = "acc_lifebank")
@PrimaryKeyJoinColumn(referencedColumnName="id_account")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalAccount extends Account implements Serializable  {
	
	@Column(name = "available", precision=9, scale=2)
	private BigDecimal available;
	
	@Column(name = "reserved", precision=9, scale=2)
	private BigDecimal reserved;

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getReserved() {
		return reserved;
	}

	public void setReserved(BigDecimal reserved) {
		this.reserved = reserved;
	}

}
