package com.lifebank.banksProducts.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lifebank.banksProducts.model.domain.Account;
import com.lifebank.banksProducts.model.domain.CreditAccount;
import com.lifebank.banksProducts.model.domain.PersonalAccount;
import com.lifebank.banksProducts.parse.ResponseAccountParse;
import com.lifebank.banksProducts.response.CreditAccountResponse;
import com.lifebank.banksProducts.response.LoanAccountResponse;
import com.lifebank.banksProducts.response.PersonalAccountResponse;
import com.lifebank.banksProducts.services.CustomerService;
import com.lifebank.banksProducts.util.DefferenceBetweenDate;
import com.lifebank.banksProducts.util.IdentifierAccountType;

@RestController
@RequestMapping("/accounts/")
public class TransactionsByAccountController {
	
	@Autowired
	private ResponseAccountParse responseAccountParse;
	
	@Autowired
	private IdentifierAccountType identifierAccountType;
	
	@Autowired 
	private CustomerService customerService;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value = "/{idCustomer}/{idAccount}", method=RequestMethod.GET)
	public ResponseEntity<?> transactionsAccount(@PathVariable("idAccount")String idAccount,
			@PathVariable("idCustomer")String idCustomer,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate){
		ResponseEntity<?> response = null;
		boolean ownAccount = false;
		
		try {ownAccount = customerService.getCustomer(idCustomer).containsAccount(idAccount);} catch(Exception e){
		}
		
		if(ownAccount){
			String accountType = identifierAccountType.identifierType(idAccount);
			Date strDat = null;
			Date endDat = null;
			boolean downThreeMonths=false;
			try{
				strDat = dateFormat.parse(startDate);
				endDat = dateFormat.parse(endDate);
				int diffMonths = DefferenceBetweenDate.defferenceInMonths(strDat, endDat);
				if(diffMonths>=0 && diffMonths<=3){
					downThreeMonths = true;
				}
			} catch(Exception e){
				response = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
			}
			if(strDat != null && endDat != null ){
				if(downThreeMonths){
					if(accountType.equals(PersonalAccount.class.getSimpleName())){
						PersonalAccountResponse personalAccount = responseAccountParse.responsePersonalAccount(idAccount,strDat,endDat);
						response = new ResponseEntity<PersonalAccountResponse>(personalAccount,HttpStatus.OK);
					} else{
						if(accountType.equals(CreditAccount.class.getSimpleName())){
							CreditAccountResponse creditAccount = responseAccountParse.responseCreditAccount(idAccount,strDat,endDat);
							response = new ResponseEntity<CreditAccountResponse>(creditAccount,HttpStatus.OK);
						} else {
							LoanAccountResponse loanAccount = responseAccountParse.responseLoanAccount(idAccount,strDat,endDat);
							response = new ResponseEntity<LoanAccountResponse>(loanAccount,HttpStatus.OK);
						}
					}
				} else {
					response = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
				} 
			}
			
		} else {
			response = new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	
	public void setTypeAccount(String idAccount){
		
	}
}
