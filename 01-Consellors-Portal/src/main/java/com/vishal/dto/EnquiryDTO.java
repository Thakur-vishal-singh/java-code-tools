package com.vishal.dto;

import lombok.Data;

@Data
public class EnquiryDTO {
	
//	to capture the form data we use this class 
//	this for view Enquires view data also

	private Integer enqId;
	private String stuName;
	private String stuPhno;
	private String classMode;
	private String course;
	private String enqStatus;
	
	
	
}
