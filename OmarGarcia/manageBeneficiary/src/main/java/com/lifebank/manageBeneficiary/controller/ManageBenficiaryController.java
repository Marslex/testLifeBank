package com.lifebank.manageBeneficiary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifebank.manageBeneficiary.model.domain.Beneficiary;
import com.lifebank.manageBeneficiary.services.BeneficiaryService;

@RestController
@RequestMapping("/manageBeneficiaries/")
public class ManageBenficiaryController {
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	ResponseEntity<List<Beneficiary>> getAll(@PathVariable("id") Long id){
		//List<Beneficiary> roles = beneficiaryService.getBeneficiaries(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
