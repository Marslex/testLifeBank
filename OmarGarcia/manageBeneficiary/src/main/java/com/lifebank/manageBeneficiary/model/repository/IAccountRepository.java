package com.lifebank.manageBeneficiary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifebank.manageBeneficiary.model.domain.Account;

@Repository("accountRepository")
public interface IAccountRepository extends JpaRepository<Account, String> {
	/*
	@Query("select acc.idAccount, acc.startDate from Account acc where 1=1 and acc.customer = :customer")
	public List<Object> findByCustomer(@Param("customer")Customer customer);
	*/
	
}
