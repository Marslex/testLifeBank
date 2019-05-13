package com.lifebank.payOwnCreditAccount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnCreditAccount.model.domain.Account;
import com.lifebank.payOwnCreditAccount.model.repository.IAccountRepository;

@Service("accountService")
public class AccountService {
	
	@Autowired 
	private IAccountRepository accountRepository;
	
	public Account getById(String id){
		return accountRepository.getOne(id);
	}
}
