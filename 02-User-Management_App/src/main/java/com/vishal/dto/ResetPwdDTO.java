package com.vishal.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;

import lombok.Data;

@Data
public class ResetPwdDTO {

	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	
}
