package com.lifebank.payOwnCreditAccount.util;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("identifierAccountType")
@PropertySource("classpath:accountsType.properties")
public class IdentifierAccountType {
	
	private Map<String,String> accountTypes = new HashMap<String,String>();
	
	@Value("${accounts_type}") 
	String accType;
	
	@Value("${identifier_type_by_id}") 
	String accIdentifier;
	
	@PostConstruct
    public void init() {
		String[] accTypes = accType.split(",");
		String[] accIdentifiers = accIdentifier.split(",");
		for (int i = 0 ; i < accTypes.length; i++){
			accountTypes.put(accTypes[i], accIdentifiers[i]);
		}
    }
	
	public String identifierType(String idAccount){
		String clase= null;
		for(HashMap.Entry<String, String> entry : accountTypes.entrySet()){
				if(idAccount.substring(0, 3).equals(entry.getValue())){
					clase = entry.getKey();
				}
		}
		return clase;
	}
}
