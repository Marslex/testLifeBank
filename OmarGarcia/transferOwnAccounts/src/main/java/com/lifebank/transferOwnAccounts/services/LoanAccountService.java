package com.lifebank.transferOwnAccounts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifebank.transferOwnAccounts.model.domain.Customer;
import com.lifebank.transferOwnAccounts.model.domain.LoanAccount;
import com.lifebank.transferOwnAccounts.model.repository.ILoanAccountRepository;

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
