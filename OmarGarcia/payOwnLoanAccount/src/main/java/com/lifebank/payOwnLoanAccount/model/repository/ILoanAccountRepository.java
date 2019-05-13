package com.lifebank.payOwnLoanAccount.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.payOwnLoanAccount.model.domain.Customer;
import com.lifebank.payOwnLoanAccount.model.domain.LoanAccount;

@Repository("loanAccountRepository")
public interface ILoanAccountRepository extends JpaRepository<LoanAccount, String> {

	public List<LoanAccount> findByCustomer(Customer customer);
}
