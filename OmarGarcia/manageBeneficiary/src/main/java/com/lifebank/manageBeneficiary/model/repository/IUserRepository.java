package com.lifebank.manageBeneficiary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lifebank.manageBeneficiary.model.domain.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserName(String userName);
	
}
