package com.vishal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entitiy.EnquiryEntity;

public  interface EnquiryRepo extends JpaRepository<EnquiryEntity, Integer> {
 
//  we cannot write the directly blow code because there is not counsellorId there so we need to to take object with the object we call the counsellorId which is inside the CounsellorEnitity
//  public List<EnquiryEntity> findByCounsellorId(Integer counsellorId);
//                             || we have to use lower code
	public List<EnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);
	
	
	
}
