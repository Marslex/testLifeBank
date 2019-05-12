package com.lifebank.transferOwnAccounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.transferOwnAccounts.model.domain.User;
import com.lifebank.transferOwnAccounts.model.repository.IUserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User getUser(String userName){
		return userRepository.findByUserName(userName);
	}
}
