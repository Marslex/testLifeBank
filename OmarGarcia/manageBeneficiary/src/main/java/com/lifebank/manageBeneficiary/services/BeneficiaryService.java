package com.lifebank.manageBeneficiary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.manageBeneficiary.model.domain.Beneficiary;
import com.lifebank.manageBeneficiary.model.domain.Customer;
import com.lifebank.manageBeneficiary.model.repository.IBeneficiaryRepository;

@Service("beneficiaryService")
public class BeneficiaryService {

	@Autowired
	private IBeneficiaryRepository beneficiaryRepository;
	
	public List<Beneficiary> getBeneficiaries(Customer customer){
		return beneficiaryRepository.findByCustomer(customer);
	}
	
	public Beneficiary findByCustomerAndAccount(Customer customer, String account){
		return beneficiaryRepository.findByCustomerAndAccount(customer, account);
	}
	
	public Beneficiary updateBeneficiary(Beneficiary beneficiary){
		return beneficiaryRepository.saveAndFlush(beneficiary);
	}
	
	public void deleteBeneficiary(Beneficiary beneficiary){
		beneficiaryRepository.delete(beneficiary);
	}
	
	public Beneficiary saveBeneficiary(Beneficiary beneficiary){
		return beneficiaryRepository.saveAndFlush(beneficiary);
	}
	
}
