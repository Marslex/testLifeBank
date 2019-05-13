package com.lifebank.payOwnCreditAccount.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lifebank.payOwnCreditAccount.model.domain.CreditAccount;
import com.lifebank.payOwnCreditAccount.model.domain.Customer;

@Repository("creditAccountRepository")
public interface ICreditAccountRepository extends JpaRepository<CreditAccount, String> {

	public List<CreditAccount> findByCustomer(Customer customer);
}
