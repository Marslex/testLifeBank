package com.lifebank.transferOwnAccounts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.transferOwnAccounts.model.domain.CreditAccount;
import com.lifebank.transferOwnAccounts.model.domain.Customer;
import com.lifebank.transferOwnAccounts.model.repository.ICreditAccountRepository;

@Service("creditAccountService")
public class CreditAccountService {

	@Autowired
	private ICreditAccountRepository creditAccountRepository;
	
	public List<CreditAccount> getCreditAccounts(Customer customer){
		return creditAccountRepository.findByCustomer(customer);
	}
	
	public CreditAccount getById(String idAccount){
		return creditAccountRepository.getOne(idAccount);
	}
}
