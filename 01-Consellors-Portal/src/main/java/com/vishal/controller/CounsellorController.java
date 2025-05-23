package com.vishal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vishal.dto.CounsellorDTO;
import com.vishal.dto.DashboardResponseDTO;
import com.vishal.service.CounsellorService;
import com.vishal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enqService;
	
	
	@GetMapping("/")
	public String index(Model model) {
	
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		session.invalidate();   		
		
		CounsellorDTO cdto = new CounsellorDTO();//
		model.addAttribute("counsellor", cdto);//  if you want to write this code again and again you can return "redirect:/";
		
//		once we click the logout it will delete the session and go to the / index method
		return "index";
	}
	
	
	@PostMapping("/login")
	public String handleLogin(HttpServletRequest req,CounsellorDTO counsellor, Model model) {
		
		CounsellorDTO counsellorDTO = counsellorService.login(counsellor);
		
		// if this not a valid login id then it will go in the if and show the invalid message
		if(counsellorDTO == null) {
			
			CounsellorDTO cdto = new CounsellorDTO();
			model.addAttribute("counsellor",cdto);   //if we don't write this line we need to use this in method @ModelAttribute("counsellor")CounsellorDTO counsellor,
			
			model.addAttribute("emsg","Invalid Creadential");
			return "index";
			// if the login id is then it will enter in the else block
		}else {
			Integer counsellorId = counsellorDTO.getCounsellorId();
			
			//  store the cousnellorId in http session object till in the end of the session
			HttpSession session =req.getSession(true);// true means it will going to give you new session id
			session.setAttribute("counsellorId", counsellorId); 
			
			DashboardResponseDTO dashboardDto = enqService.getDashboardInfo(counsellorId);
			
			model.addAttribute("dashboardDto", dashboardDto);
			
			return "dashboard";
		}
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		
		return "register";
	}
	
	@PostMapping("/register")// @ModelAttribute this we use because when the page is reload it should give the data 
	public String handleRegister(@ModelAttribute("counsellor")CounsellorDTO counsellor, Model model) {
		boolean unique = counsellorService.uniqueEmailCheck(counsellor.getEmail());
		
		if(unique) {
			
			boolean register = counsellorService.register(counsellor);
			
			if(register) {
				model.addAttribute("smsg", "Registation Success");
			}else {
				model.addAttribute("emsg", "Registation Failed");
			}
			
		}else {
			model.addAttribute("emsg", "Enter unique Email");
		}
		return "register";
	}

	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		DashboardResponseDTO dashboardDto = enqService.getDashboardInfo(counsellorId);
		
		model.addAttribute("dashboardDto",dashboardDto);
		
		return "dashboard";
	}

}
