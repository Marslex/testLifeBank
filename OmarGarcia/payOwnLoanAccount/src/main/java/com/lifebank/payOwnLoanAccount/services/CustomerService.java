package com.lifebank.payOwnLoanAccount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnLoanAccount.model.domain.Customer;
import com.lifebank.payOwnLoanAccount.model.repository.ICustomerRepository;

@Service("customerService")
public class CustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	public Customer getCustomer(String dui){
		return customerRepository.findByDui(dui); 
	}
}
