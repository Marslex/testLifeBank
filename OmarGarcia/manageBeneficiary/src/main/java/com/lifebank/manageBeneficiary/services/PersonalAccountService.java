package com.lifebank.manageBeneficiary.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifebank.manageBeneficiary.model.domain.Customer;
import com.lifebank.manageBeneficiary.model.domain.PersonalAccount;
import com.lifebank.manageBeneficiary.model.repository.IPersonalAccountRepository;

@Service("personalAccountService")
public class PersonalAccountService {

	@Autowired
	private IPersonalAccountRepository personalAccountRepository;
	
	public List<PersonalAccount> getPersonalAccounts(Customer customer){
		return personalAccountRepository.findByCustomer(customer);
	}
	
	public PersonalAccount getById(String idAccount){
		return personalAccountRepository.getOne(idAccount);
	}
	
	public List<PersonalAccount> getPersonalAccounts(List<String> accounts){
		return personalAccountRepository.findAllById(accounts);
	}
	
	public List<PersonalAccount> saveAccounts(List<PersonalAccount> accounts){
		return personalAccountRepository.saveAll(accounts);
	}
	
	public PersonalAccount saveAccount(PersonalAccount account){
		return personalAccountRepository.saveAndFlush(account);
	}
}
