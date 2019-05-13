package com.lifebank.payOwnLoanAccount.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="customer", schema = "cus_lifebank")
public class Customer implements Serializable {
	
	@Id
	@Column(name = "dui", unique = true, length = 9)
	private String dui;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "last_anme", length = 100)
	private String lastName;
	
	@Column(name = "telefono", length = 30)
	private String tel;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean containsAccount(String idAccount){
		boolean containsAccount = false;
		for(Account account : accounts){
			if(account.getIdAccount().equals(idAccount)){
				containsAccount = true;
			}
		}
		return containsAccount; 
	}
}
