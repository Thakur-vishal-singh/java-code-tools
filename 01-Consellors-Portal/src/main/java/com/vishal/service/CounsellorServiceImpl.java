package com.vishal.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishal.dto.CounsellorDTO;
import com.vishal.entitiy.CounsellorEntity;
import com.vishal.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) { // copy entity to the DTO  
//		if this not equal null then if statement run
     CounsellorEntity counEntity =  counsellorRepo.findByEmailAndPwd(counsellorDTO.getEmail(), counsellorDTO.getPwd());
     
     if(counEntity != null) {
    	//copy the entity object into the DTO object and return DTO object :::: there three way to do this let see one by one
    	CounsellorDTO counDto = new CounsellorDTO();

//      we use the CounsellorEntitiy like setter and getter but we have use 100 setter and getters that is not good practices so we have to go for the BeanUtiles or ModelMapper
//    	CounsellorEntitiy counEntitiy = new CounsellorEntitiy();
//      counDto.setEmail(counEntitiy.getEmail());
//    	counDto.setName(counEntitiy.getName());
//    	counDto.setCounsellorId(counEntitiy.getConsellorId());
    	
//      this save the code writing again and again we are doing like same as setters and getters here 
    	 BeanUtils.copyProperties(counEntity, counDto);
    	 return counDto;
    	
     }
		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
       CounsellorEntity counEntity =  counsellorRepo.findByEmail(email);
//		if(counEntity == null ) {
//			return true;
//		}
//       return false;  we can use directly use the blow code same work if the email is not in the table then true else false 
       return counEntity == null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) { // copy DTO to the entity
		
		CounsellorEntity counEntity = new CounsellorEntity();
	    BeanUtils.copyProperties(counsellorDTO, counEntity);// it will copy the counsellorDTO  into the counsellorEntity
        
	   CounsellorEntity savedEntity =  counsellorRepo.save(counEntity);// save will take the entity object but we want the DTO
    
		return null != savedEntity.getCounsellorId();// if this return true then successful register or else not register
	} 
}
