package com.vishal.dto;

import lombok.Data;

@Data
public class DashboardResponseDTO {
	// DTO stands for Data Transfer Object it's used to transfer the data UI to controller  
	// Entity is for The Transfer the data into the Tables

	
	private Integer totalEnqCnt;
	private Integer openEnqCnt;
	private Integer enrolledEnqCnt;
	private Integer LostEnqCnt;
	// this for the getting view the dash-bord request 
	
}
