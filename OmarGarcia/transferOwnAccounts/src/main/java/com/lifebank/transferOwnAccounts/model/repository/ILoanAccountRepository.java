package com.lifebank.transferOwnAccounts.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.transferOwnAccounts.model.domain.Customer;
import com.lifebank.transferOwnAccounts.model.domain.LoanAccount;

@Repository("loanAccountRepository")
public interface ILoanAccountRepository extends JpaRepository<LoanAccount, String> {

	public List<LoanAccount> findByCustomer(Customer customer);
}
