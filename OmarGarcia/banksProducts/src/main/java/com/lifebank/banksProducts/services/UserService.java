package com.lifebank.banksProducts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifebank.banksProducts.model.domain.User;
import com.lifebank.banksProducts.model.repository.IUserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User getUser(String userName){
		return userRepository.findByUserName(userName);
	}
}
