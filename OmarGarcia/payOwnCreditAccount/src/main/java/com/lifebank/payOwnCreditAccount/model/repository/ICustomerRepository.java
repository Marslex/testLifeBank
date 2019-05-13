package com.lifebank.payOwnCreditAccount.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.payOwnCreditAccount.model.domain.Customer;

@Repository("customerRepository")
public interface ICustomerRepository extends JpaRepository<Customer, String> {
	
	public Customer findByDui(String dui);
}
