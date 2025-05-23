package com.vishal.service;

import java.util.List;

import com.vishal.dto.DashboardResponseDTO;
import com.vishal.dto.EnqFilterDTO;
import com.vishal.dto.EnquiryDTO;

public interface EnquiryService {
	
	
//	basted on the counselor id we will get all the enquire data which is handle by the counselor (in the enquire table we have the foreign key which is counselor id )
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId);// to view the dash-board 
	
	
//	first parameter is for the enquire data saving enqDTO. second is for which consoler is adding this enquire    
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId);// it's for the adding the Enquire 
	
	
//	is for getting the all enquires   
	public List<EnquiryDTO> getEnquiries(Integer counsellorId);
//	                 ||
//	                 ||  this two are the overloading method based 
//	                 ||
//   is for the filter enquires based on selected filters 
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId);
	
	
//	in the view enquire we have option to edit based on that we will get the data to edit 
	public EnquiryDTO getEnquiryById(Integer enqId);
}
