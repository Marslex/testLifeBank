package com.lifebank.banksProducts.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lifebank.banksProducts.model.domain.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserName(String userName);
	
}
