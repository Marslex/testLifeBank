package com.lifebank.payOwnLoanAccount.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnLoanAccount.model.domain.Account;
import com.lifebank.payOwnLoanAccount.model.domain.Transaction;
import com.lifebank.payOwnLoanAccount.model.repository.ITransactionRepository;

@Service("transactionService")
public class TransactionService {

	@Autowired
	ITransactionRepository transactionRepository;
	
	public List<Transaction> getTransactions(Date startDate, Date endDate, Account account){
		return transactionRepository.findByTransactionDateBetweenAndAccount(startDate, endDate, account);
	}
	
	public boolean existsTransaction(String transactionNumber){
		return transactionRepository.existsById(transactionNumber);
	}
	
}
