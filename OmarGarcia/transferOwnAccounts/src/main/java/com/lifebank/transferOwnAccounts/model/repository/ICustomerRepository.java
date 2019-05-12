package com.lifebank.transferOwnAccounts.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.transferOwnAccounts.model.domain.Customer;

@Repository("customerRepository")
public interface ICustomerRepository extends JpaRepository<Customer, String> {
	
	public Customer findByDui(String dui);
}
