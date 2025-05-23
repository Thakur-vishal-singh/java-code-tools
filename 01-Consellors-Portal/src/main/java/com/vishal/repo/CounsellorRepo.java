package com.vishal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entitiy.CounsellorEntity;

public interface CounsellorRepo  extends JpaRepository<CounsellorEntity, Integer> {

	public CounsellorEntity findByEmailAndPwd(String email, String pwd);
	
	public CounsellorEntity findByEmail(String email);
	
	
}
