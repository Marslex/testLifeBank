package com.lifebank.banksProducts.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lifebank.banksProducts.model.domain.CreditAccount;
import com.lifebank.banksProducts.model.domain.Customer;
import com.lifebank.banksProducts.model.domain.LoanAccount;
import com.lifebank.banksProducts.model.domain.PersonalAccount;
import com.lifebank.banksProducts.services.CreditAccountService;
import com.lifebank.banksProducts.services.CustomerService;
import com.lifebank.banksProducts.services.LoanAccountService;
import com.lifebank.banksProducts.services.PersonalAccountService;

/*
 * 
 * ProductosBancarios
 * Usada para indicar al cliente los productos bancarios que este posee.
 * 
 * */

@RestController
@RequestMapping("/accounts")
public class BanksProductsController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired 
	private PersonalAccountService personalAccountService;
	
	@Autowired 
	private CreditAccountService creditAccountService;
	
	@Autowired
	private LoanAccountService loanAccountService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String,HashMap<String,List>>> accounts(@RequestParam("idCustomer") String idCustomer){
		HashMap<String,HashMap<String,List>> accounts = new HashMap<String,HashMap<String,List>>();
		HashMap<String,List> typeAccounts = new HashMap<String,List>();
		ResponseEntity<HashMap<String,HashMap<String,List>>> response = null;
		Customer customer = customerService.getCustomer(idCustomer);
		
		if(customer != null){
			List<PersonalAccount> personalAccounts = personalAccountService.getPersonalAccounts(customer);
			List<CreditAccount> creditAccounts = creditAccountService.getCreditAccounts(customer);
			List<LoanAccount> loanAccounts = loanAccountService.getLoanAccounts(customer);
			
			if(!personalAccounts.isEmpty()){
				for(PersonalAccount account : personalAccounts){
					account.setCustomer(null);
					account.setEndDate(null);
					account.setStartDate(null);
					account.setState(null);
					account.setTransactions(null);
					account.setAvailable(null);
					account.setReserved(null);
				}
				typeAccounts.put("personal", personalAccounts);
			}
			
			if(!creditAccounts.isEmpty()){
				for(CreditAccount account : creditAccounts){
					account.setCustomer(null);
					account.setEndDate(null);
					account.setStartDate(null);
					account.setState(null);
					account.setTransactions(null);
					account.setAvailable(null);
					account.setInterestAmount(null);
					account.setInterestRate(null);
					account.setLimit(null);
					account.setMonthlyCut(null);
				}
				typeAccounts.put("credit", creditAccounts);
			}
			if(!loanAccounts.isEmpty()){
				for(LoanAccount account : loanAccounts){
					account.setCustomer(null);
					account.setEndDate(null);
					account.setStartDate(null);
					account.setState(null);
					account.setTransactions(null);
					account.setInterestAmount(null);
					account.setInterestRate(null);
					account.setDebt(null);
					account.setTotal(null);
				}
				typeAccounts.put("credit", creditAccounts);
			}
			
			if(typeAccounts.size() != 0){
				accounts.put("accounts", typeAccounts);
			}
			if(accounts.size()!=0){
				response = new ResponseEntity<HashMap<String,HashMap<String,List>>>(accounts,HttpStatus.OK);
			} else {
				response = new ResponseEntity<HashMap<String,HashMap<String,List>>>(HttpStatus.OK);
			}
		} else {
			response = new ResponseEntity<HashMap<String,HashMap<String,List>>>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
}
