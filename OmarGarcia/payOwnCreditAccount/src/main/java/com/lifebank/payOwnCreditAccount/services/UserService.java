package com.lifebank.payOwnCreditAccount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.payOwnCreditAccount.model.domain.User;
import com.lifebank.payOwnCreditAccount.model.repository.IUserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User getUser(String userName){
		return userRepository.findByUserName(userName);
	}
}
