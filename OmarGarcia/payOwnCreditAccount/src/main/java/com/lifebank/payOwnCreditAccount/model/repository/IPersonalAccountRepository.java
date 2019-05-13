package com.lifebank.payOwnCreditAccount.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.payOwnCreditAccount.model.domain.Customer;
import com.lifebank.payOwnCreditAccount.model.domain.PersonalAccount;

@Repository("personalAccountRepository")
public interface IPersonalAccountRepository extends JpaRepository<PersonalAccount, String> {

	//@Query("select acc.idAccount, acc.startDate from PersonalAccount acc "+ "where 1=1 and acc.customer = :customer")
	public List<PersonalAccount> findByCustomer(Customer customer);
}
