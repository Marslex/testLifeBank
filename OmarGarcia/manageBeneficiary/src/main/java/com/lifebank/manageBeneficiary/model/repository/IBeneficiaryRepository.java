package com.lifebank.manageBeneficiary.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lifebank.manageBeneficiary.model.domain.Beneficiary;
import com.lifebank.manageBeneficiary.model.domain.Customer;

@Repository("beneficiaryRepository")
public interface IBeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	public List<Beneficiary> findByCustomer(Customer customer);
	
	public Beneficiary findByCustomerAndAccount(Customer customer, String account);
}
