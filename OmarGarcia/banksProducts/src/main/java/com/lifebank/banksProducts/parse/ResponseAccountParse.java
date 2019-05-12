package com.lifebank.banksProducts.parse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lifebank.banksProducts.model.domain.Account;
import com.lifebank.banksProducts.model.domain.CreditAccount;
import com.lifebank.banksProducts.model.domain.LoanAccount;
import com.lifebank.banksProducts.model.domain.PersonalAccount;
import com.lifebank.banksProducts.response.CreditAccountResponse;
import com.lifebank.banksProducts.response.LoanAccountResponse;
import com.lifebank.banksProducts.response.PersonalAccountResponse;
import com.lifebank.banksProducts.services.CreditAccountService;
import com.lifebank.banksProducts.services.LoanAccountService;
import com.lifebank.banksProducts.services.PersonalAccountService;
import com.lifebank.banksProducts.services.TransactionService;

@Component("responseAccountParse")
public class ResponseAccountParse {
	
	@Autowired
	private CreditAccountService creditAccountService;
	
	@Autowired
	private LoanAccountService loanAccountService;
	
	@Autowired
	private PersonalAccountService personalAccountService;
	
	@Autowired
	private TransactionService transactionService;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public PersonalAccountResponse responsePersonalAccount(String idAccount,Date startDate,Date endDate){
		PersonalAccount personalAccount = personalAccountService.getById(idAccount);
		PersonalAccountResponse personalAccountResponse = new PersonalAccountResponse(); 
		personalAccountResponse.setId(personalAccount.getIdAccount());
		personalAccountResponse.setStartDate(dateFormat.format(startDate));
		personalAccountResponse.setEndDate(dateFormat.format(endDate));
		personalAccountResponse.setTransactions(transactionService.getTransactions(startDate, endDate, (Account) personalAccount));
		
		return personalAccountResponse;
	}
	
	public CreditAccountResponse responseCreditAccount(String idAccount,Date startDate, Date endDate){
		CreditAccount creditAccount = creditAccountService.getById(idAccount);
		CreditAccountResponse creditAccountResponse = new CreditAccountResponse(); 
		creditAccountResponse.setId(creditAccount.getIdAccount());
		creditAccountResponse.setStartDate(dateFormat.format(startDate));
		creditAccountResponse.setEndDate(dateFormat.format(endDate));
		creditAccountResponse.setLimit(creditAccount.getLimit().doubleValue());
		creditAccountResponse.setAvailable(creditAccount.getAvailable().doubleValue());
		creditAccountResponse.setInterestRate(creditAccount.getInterestRate().doubleValue());
		creditAccountResponse.setInterestAmount(creditAccount.getInterestAmount().doubleValue());
		creditAccountResponse.setMonthlyCut(creditAccount.getMonthlyCut());
		creditAccountResponse.setTransactions(transactionService.getTransactions(startDate, endDate, (Account) creditAccount));
			
		return creditAccountResponse;
	}
	
	public LoanAccountResponse responseLoanAccount(String idAccount,Date startDate, Date endDate){
		LoanAccount loanAccount = loanAccountService.getById(idAccount);
		LoanAccountResponse loanAccountResponse = new LoanAccountResponse(); 
		loanAccountResponse.setId(loanAccount.getIdAccount());
		loanAccountResponse.setStartDate(dateFormat.format(startDate));
		loanAccountResponse.setEndDate(dateFormat.format(endDate));
		loanAccountResponse.setTotal(loanAccount.getTotal().doubleValue());
		loanAccountResponse.setDebt(loanAccount.getDebt().doubleValue());
		loanAccountResponse.setInterestRate(loanAccount.getInterestRate().doubleValue());
		loanAccountResponse.setInterestAmount(loanAccount.getInterestAmount().doubleValue());
		loanAccountResponse.setTransactions(transactionService.getTransactions(startDate, endDate, (Account) loanAccount));
		
		return loanAccountResponse;
	}
}
