package com.lifebank.payOwnLoanAccount.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.payOwnLoanAccount.model.domain.Account;
import com.lifebank.payOwnLoanAccount.model.domain.Transaction;

@Repository("transactionRepository")
public interface ITransactionRepository extends JpaRepository<Transaction, String> {

	public List<Transaction> findByTransactionDateBetweenAndAccount(Date startDate, Date endDate, Account account);
	
}
