package com.lifebank.transferOwnAccounts.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.transferOwnAccounts.model.domain.Account;
import com.lifebank.transferOwnAccounts.model.domain.Transaction;

@Repository("transactionRepository")
public interface ITransactionRepository extends JpaRepository<Transaction, String> {

	public List<Transaction> findByTransactionDateBetweenAndAccount(Date startDate, Date endDate, Account account);
	
}
