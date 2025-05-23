package com.vishal.dto;

import lombok.Data;
// dto also know as binding 
@Data 
public class QuoteResponseDTO {

	private Integer id;
	private String quote;
	private String author;
	
	
}
