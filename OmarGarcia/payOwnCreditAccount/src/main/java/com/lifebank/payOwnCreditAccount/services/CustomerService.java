package com.lifebank.payOwnCreditAccount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnCreditAccount.model.domain.Customer;
import com.lifebank.payOwnCreditAccount.model.repository.ICustomerRepository;

@Service("customerService")
public class CustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	public Customer getCustomer(String dui){
		return customerRepository.findByDui(dui); 
	}
}
