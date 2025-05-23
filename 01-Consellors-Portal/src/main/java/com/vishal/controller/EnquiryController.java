package com.vishal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.dto.EnqFilterDTO;
import com.vishal.dto.EnquiryDTO;
import com.vishal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	
	
	@GetMapping("/edit-enquiry")
	public String editEnquiry(@RequestParam("enqId")Integer enqId,Model model) {
		
		EnquiryDTO enqDto = enqService.getEnquiryById(enqId);
		
//		EnquiryDTO enqDto = new EnquiryDTO();
		model.addAttribute("enquiry",enqDto);
		
		return "add-enquiry"; 
	}
	
	
	
	
	@GetMapping("/enquiry-page")
	public String loadEnqPage(Model model) {
		
		EnquiryDTO enqDto = new EnquiryDTO();
		model.addAttribute("enquiry", enqDto);
		
		return "add-enquiry";
	}
	
	
	// HttpServletRequest req:::: is for the getting the counselor id from the session 
	// @ModelAttribute("enquiry")EnuiryDTO enquiry :::: it's used after the form submit the value should be there to display
	@PostMapping("/add-enquiry") // Model model :::: is used to send the data from controller to ui
	public String addEnquiry(HttpServletRequest req,@ModelAttribute("enquiry")EnquiryDTO enquiryDTO, Model model) {
	 
		HttpSession session = req.getSession(false);// it will give http session 
		Integer counsellorId = (Integer)session.getAttribute("counsellorId"); // from that session we will get the session id
		boolean status = enqService.addEnquiry(enquiryDTO, counsellorId);
		if(status) {
			model.addAttribute("smsg","Enquiry Saved");
		}else {
			model.addAttribute("emsg", "Failed to Save Enquiry");
		}
		
		return "add-enquiry";
	}
	
	
	
	
	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		List<EnquiryDTO> enqList= enqService.getEnquiries(counsellorId);
		
		model.addAttribute("enquires", enqList);
		
		EnqFilterDTO filterDTO = new EnqFilterDTO();
		model.addAttribute("filterDTO",filterDTO);
		 
		return "view-enquiries";
	}
	
	
	
	
	@PostMapping("/filter-enquiries")
	public String filterEnquires(HttpServletRequest req,@ModelAttribute("filterDTO")EnqFilterDTO filterDTO, Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<EnquiryDTO> enqList= enqService.getEnquiries(filterDTO, counsellorId);
		
		model.addAttribute("enquires", enqList);
		
		return "view-enquiries";
	}
	
	
	
}
