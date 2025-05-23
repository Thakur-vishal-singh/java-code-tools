package com.vishal.service;

import com.vishal.dto.CounsellorDTO;

public interface CounsellorService {

//      for valid login return id, for invalid return 0
//		public Integer login(CounsellorDTO counsellorDto);
//		                        ||  both are same but the return type is change and both are valid
		public CounsellorDTO login(CounsellorDTO counsellorDTO);
//      if the id is right then return id if the id is wrong then null value we will get
		
		
//		if unique return true else false
		public boolean uniqueEmailCheck(String email);// email unique check in the registration   
		
	
//		if register successful then return true if not then false
		public boolean register(CounsellorDTO counsellorDTO);// to check the registration fail or success  

	
}
