package com.lifebank.manageBeneficiary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifebank.manageBeneficiary.model.domain.Customer;
import com.lifebank.manageBeneficiary.model.domain.LoanAccount;
import com.lifebank.manageBeneficiary.model.repository.ILoanAccountRepository;

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
	
	public LoanAccount saveLoanAccount(LoanAccount account){
		return loanAccountRepository.saveAndFlush(account);
	}
}
