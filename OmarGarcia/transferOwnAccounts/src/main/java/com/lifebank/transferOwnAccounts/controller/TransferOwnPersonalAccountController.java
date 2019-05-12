package com.lifebank.transferOwnAccounts.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lifebank.transferOwnAccounts.model.domain.Account;
import com.lifebank.transferOwnAccounts.model.domain.Customer;
import com.lifebank.transferOwnAccounts.model.domain.PersonalAccount;
import com.lifebank.transferOwnAccounts.model.domain.Transaction;
import com.lifebank.transferOwnAccounts.request.TransferPersonalAccountRequest;
import com.lifebank.transferOwnAccounts.services.CustomerService;
import com.lifebank.transferOwnAccounts.services.PersonalAccountService;
import com.lifebank.transferOwnAccounts.services.TransactionService;
import com.lifebank.transferOwnAccounts.util.IdentifierAccountType;

@RestController
@RequestMapping("/accounts/")
public class TransferOwnPersonalAccountController {

	@Autowired 
	private CustomerService customerService;
	
	@Autowired
	private IdentifierAccountType identifierType;
	
	@Autowired 
	private PersonalAccountService personalAccountService;
	
	@Autowired
	private TransactionService transactionService;
	 
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	
	@RequestMapping(value = "/{idCustomer}/", method=RequestMethod.POST)
	public ResponseEntity<Map<String,String>> transferOwnPersonalAccount(@RequestBody TransferPersonalAccountRequest transferPersonalAccountRequest,
			@PathVariable("idCustomer") String idCustomer
			){
		ResponseEntity<Map<String,String>> response = null;
	
		Customer customer =  customerService.getCustomer(idCustomer);
		String typeAccountOrigin = identifierType.identifierType(transferPersonalAccountRequest.getIdAccountOrigin());
		String typeAccountDestination = identifierType.identifierType(transferPersonalAccountRequest.getIdaccountDestination());
		
		boolean ownAccountAndPersonalAccounts = false;
		
		try{
			if(customer.containsAccount(transferPersonalAccountRequest.getIdAccountOrigin()) 
					&& customer.containsAccount(transferPersonalAccountRequest.getIdaccountDestination())
					&& PersonalAccount.class.getSimpleName().equals(typeAccountOrigin) 
					&& typeAccountDestination.equals(typeAccountOrigin)
					){
				ownAccountAndPersonalAccounts = true;
				System.out.println("posee ambas cuentas");
			}
		}catch(Exception e){
			System.out.println("customer not found: " + idCustomer);
		}
		
		if(ownAccountAndPersonalAccounts){
			System.out.println("iniciar transferencia");
			BigDecimal transferAmount = new BigDecimal(transferPersonalAccountRequest.getTransferAmount());
			PersonalAccount originAccount = null;
			PersonalAccount destinationAccount = null;
			
			List<String> accountsIds = new ArrayList<String>();
			accountsIds.add(transferPersonalAccountRequest.getIdaccountDestination());
			accountsIds.add(transferPersonalAccountRequest.getIdAccountOrigin());
			List<PersonalAccount> accounts = personalAccountService.getPersonalAccounts(accountsIds);
			for(PersonalAccount account : accounts){
				if(account.getIdAccount().equals(transferPersonalAccountRequest.getIdAccountOrigin())){
					originAccount = account;
				} else {destinationAccount = account;}
			}
			
			if(originAccount.getAvailable().compareTo(transferAmount) >= 0){
				System.out.println("fondo apto para la transferencia");
				
				originAccount.setAvailable(originAccount.getAvailable().subtract(transferAmount));
				destinationAccount.setAvailable(destinationAccount.getAvailable().add(transferAmount));
				
				Date dateDebitTransaction = new Date();
				Date dateCreditTransaction = new Date();
				String debitTransactionNumber = dateFormat.format(dateDebitTransaction) + "1";
				String creditTransactionNumber = dateFormat.format(dateCreditTransaction) + "2" ;
				
				System.out.println("debit: " + debitTransactionNumber);
				System.out.println("credit: " + creditTransactionNumber);
				
				Transaction debitTransaction = new Transaction(dateDebitTransaction, "Transference to "+destinationAccount.getIdAccount(), transferAmount.doubleValue(),
						(Account) originAccount,"D",debitTransactionNumber);
				originAccount.addTransaction(debitTransaction);
				
				Transaction creditTransaction = new Transaction(dateCreditTransaction, "Transference from "+originAccount.getIdAccount(), transferAmount.doubleValue(),
						(Account) destinationAccount,"C",creditTransactionNumber);
				destinationAccount.addTransaction(creditTransaction);
				
				try{
				personalAccountService.saveAccounts(accounts);
				} catch(Exception e){
					response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				Map<String,String> resp = new HashMap<String,String>();
				
				if(transactionService.existsTransaction(debitTransaction.getTransactionNumber())&&transactionService.existsTransaction(creditTransaction.getTransactionNumber())){
					resp.put("autorizationNumber", debitTransaction.getTransactionNumber());
					response = new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
				} else {
					
					response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				System.out.println("no dispone de suficiente $");
				response = new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}else{
			
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//customerService.getCustomer(idCustomer).containsAccount(idAccount);
		
		return response;
	}
}
