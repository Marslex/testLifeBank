package com.lifebank.payOwnCreditAccount.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnCreditAccount.model.domain.CreditAccount;
import com.lifebank.payOwnCreditAccount.model.domain.Customer;
import com.lifebank.payOwnCreditAccount.model.repository.ICreditAccountRepository;

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
	
	public CreditAccount saveCreditAccount(CreditAccount account){
		return creditAccountRepository.saveAndFlush(account);
	}
}
