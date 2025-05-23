package com.vishal.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Integer userId;
	private String name;
	private String email;
	private Long phno;
	private String pwd;
	private String pwdUpdated;
	
	
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	
}
