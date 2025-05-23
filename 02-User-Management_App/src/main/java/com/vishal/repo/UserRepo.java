package com.vishal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	
//	public UserEntity findById(Integer userId);
	
	public UserEntity findByEmailAndPwd(String email, String pwd);
	
	public UserEntity findByEmail(String email);
		
//	public List<UserEntity> findByCountryCountryId(Integer countryId);
//	
//	public List<UserEntity> findByCityCityId(Integer cityId);
//	
//	public List<UserEntity> findByStateStateId(Integer stateId);
	
	
	
}
