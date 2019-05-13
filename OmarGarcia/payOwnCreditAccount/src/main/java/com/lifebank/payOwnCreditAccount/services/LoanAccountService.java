package com.lifebank.payOwnCreditAccount.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifebank.payOwnCreditAccount.model.domain.Customer;
import com.lifebank.payOwnCreditAccount.model.domain.LoanAccount;
import com.lifebank.payOwnCreditAccount.model.repository.ILoanAccountRepository;

@Service("loanAccountService")
public class LoanAccountService {

	@Autowired
	private ILoanAccountRepository loanAccountRepository;
	
	public List<LoanAccount> getLoanAccounts(Customer customer){
		return loanAccountRepository.findByCustomer(customer);
	}
	
	public LoanAccount getById(String idAccount){
		return loanAccountRepository.getOne(idAccount);
	}
}
