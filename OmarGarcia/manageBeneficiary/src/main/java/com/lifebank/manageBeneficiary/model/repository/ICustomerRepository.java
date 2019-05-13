package com.lifebank.manageBeneficiary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.manageBeneficiary.model.domain.Customer;

@Repository("customerRepository")
public interface ICustomerRepository extends JpaRepository<Customer, String> {
	
	public Customer findByDui(String dui);
}
