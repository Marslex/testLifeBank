package com.lifebank.payOwnLoanAccount.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lifebank.payOwnLoanAccount.model.domain.Account;
import com.lifebank.payOwnLoanAccount.model.domain.Customer;
import com.lifebank.payOwnLoanAccount.model.domain.LoanAccount;
import com.lifebank.payOwnLoanAccount.model.domain.PersonalAccount;
import com.lifebank.payOwnLoanAccount.model.domain.Transaction;
import com.lifebank.payOwnLoanAccount.request.PayOwnLoanAccountRequest;
import com.lifebank.payOwnLoanAccount.services.CustomerService;
import com.lifebank.payOwnLoanAccount.services.LoanAccountService;
import com.lifebank.payOwnLoanAccount.services.PersonalAccountService;
import com.lifebank.payOwnLoanAccount.services.TransactionService;
import com.lifebank.payOwnLoanAccount.util.IdentifierAccountType;

@RestController
@RequestMapping("/payOwnLoanAccount/")
public class PayOwnLoanAccountController {

	@Autowired 
	private CustomerService customerService;
	
	@Autowired
	private IdentifierAccountType identifierType;
	
	@Autowired 
	private PersonalAccountService personalAccountService;

	@Autowired 
	private LoanAccountService loanAccountService;
	
	@Autowired
	private TransactionService transactionService;
	 
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	
	@RequestMapping(value = "/{idCustomer}/", method=RequestMethod.POST)
	public ResponseEntity<Map<String,String>> payOwnLoanAccount(
			@RequestBody PayOwnLoanAccountRequest payOwnLoanAccountRequest,
			@PathVariable("idCustomer") String idCustomer
			){
		ResponseEntity<Map<String,String>> response = null;
	
		Customer customer =  customerService.getCustomer(idCustomer);
		String typeAccountOrigin = identifierType.identifierType(payOwnLoanAccountRequest.getIdAccountOrigin());
		String typeAccountDestination = identifierType.identifierType(payOwnLoanAccountRequest.getIdaccountDestination());
		
		boolean ownAccountAndPersonalAccounts = false;
		
		try{
			if(customer.containsAccount(payOwnLoanAccountRequest.getIdAccountOrigin()) 
					&& customer.containsAccount(payOwnLoanAccountRequest.getIdaccountDestination())
					&& PersonalAccount.class.getSimpleName().equals(typeAccountOrigin) 
					&& LoanAccount.class.getSimpleName().equals(typeAccountDestination)
					){
				ownAccountAndPersonalAccounts = true;
				System.out.println("posee ambas cuentas");
			}
		}catch(Exception e){
			System.out.println("customer not found: " + idCustomer);
		}
		
		if(ownAccountAndPersonalAccounts){
			System.out.println("iniciar pago");
			BigDecimal transferAmount = new BigDecimal(payOwnLoanAccountRequest.getTransferAmount());
			PersonalAccount originAccount = personalAccountService.getById(payOwnLoanAccountRequest.getIdAccountOrigin());
			LoanAccount destinationAccount = null;
			
			if(originAccount.getAvailable().compareTo(transferAmount) >= 0){
				System.out.println("fondo apto para la transferencia");
				destinationAccount = loanAccountService.getById(payOwnLoanAccountRequest.getIdaccountDestination());
				
				originAccount.setAvailable(originAccount.getAvailable().subtract(transferAmount));
				destinationAccount.setDebt(destinationAccount.getDebt().subtract(transferAmount));
				
				Date dateDebitTransaction = new Date();
				Date dateCreditTransaction = new Date();
				String debitTransactionNumber = dateFormat.format(dateDebitTransaction) + "1";
				String creditTransactionNumber = dateFormat.format(dateCreditTransaction) + "2" ;
				
				System.out.println("debit: " + debitTransactionNumber);
				System.out.println("credit: " + creditTransactionNumber);
				
				Transaction debitTransaction = new Transaction(dateDebitTransaction, "Pay own credit card: "+destinationAccount.getIdAccount(), transferAmount.doubleValue(),
						(Account) originAccount,"D",debitTransactionNumber);
				originAccount.addTransaction(debitTransaction);
				
				Transaction creditTransaction = new Transaction(dateCreditTransaction, "Pay from own account: "+originAccount.getIdAccount(), transferAmount.doubleValue(),
						(Account) destinationAccount,"C",creditTransactionNumber);
				destinationAccount.addTransaction(creditTransaction);
				
				try{
					originAccount = personalAccountService.saveAccount(originAccount);
					destinationAccount = loanAccountService.saveLoanAccount(destinationAccount);
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
			System.out.println("cliente no posee ambas cuentas");
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//customerService.getCustomer(idCustomer).containsAccount(idAccount);
		
		return response;
	}
}
