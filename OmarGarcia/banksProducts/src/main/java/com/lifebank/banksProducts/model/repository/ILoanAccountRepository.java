package com.lifebank.banksProducts.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.banksProducts.model.domain.Customer;
import com.lifebank.banksProducts.model.domain.LoanAccount;

@Repository("loanAccountRepository")
public interface ILoanAccountRepository extends JpaRepository<LoanAccount, String> {

	public List<LoanAccount> findByCustomer(Customer customer);
}
