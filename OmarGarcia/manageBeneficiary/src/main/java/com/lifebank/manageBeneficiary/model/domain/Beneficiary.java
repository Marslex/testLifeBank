package com.lifebank.manageBeneficiary.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@Entity
@Table(name="beneficiary", schema = "cus_lifebank")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Beneficiary implements Serializable {

	@Id
	@SequenceGenerator(name="beneficiary_id_beneficiary_seq", sequenceName="beneficiary_id_beneficiary_seq",schema="cus_lifebank",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="beneficiary_id_beneficiary_seq")
	@Column(name = "id_user")
	private Integer idBeneficiary;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_customer")
	private Customer customer;
	
	@Column(name = "id_account", length = 18)
	private String account;
	
	@Column(name = "account_type", length = 20)
	private String accountType;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "email", length = 100)
	private String email;
	
	public Integer getIdBeneficiary() {
		return idBeneficiary;
	}

	public void setIdBeneficiary(Integer idBeneficiary) {
		this.idBeneficiary = idBeneficiary;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
