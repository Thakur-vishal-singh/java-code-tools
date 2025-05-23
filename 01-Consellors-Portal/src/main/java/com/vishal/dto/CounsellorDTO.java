package com.vishal.dto;
//package com.vishal.binding;  binding or DTO one only 

import lombok.Data;

@Data  // We Give there Setter and Getters in the Entity class to access that we use Data   
public class CounsellorDTO { 
	// this for login from data and register form data access 
	// form binding 

	private String name;
	private String email;
	private String pwd;
	private String phno;
	
   	
	private Integer counsellorId;

}
