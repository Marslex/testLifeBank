package com.lifebank.payOwnLoanAccount.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="user", schema = "aut_lifebank")
public class User implements Serializable {
	
	@Id
	@SequenceGenerator(name="user_id_user_seq", sequenceName="user_id_user_seq",schema="aut_lifebank",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="user_id_user_seq")
	@Column(name = "id_user")
	private Integer idUser;
	
	@Column(name = "user_name", length = 100)
	private String userName;
	
	@Column(name = "password", length = 500)
	private String password;
	
	@Column(name = "state")
	private boolean state;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_customer")
	private Customer customer;
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
