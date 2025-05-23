package com.vishal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vishal.dto.QuoteResponseDTO;
import com.vishal.dto.ResetPwdDTO;
import com.vishal.dto.UserDTO;
import com.vishal.service.UserService;

@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		UserDTO userDto = new UserDTO();
		model.addAttribute("user",userDto);
		
		return "index";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDTO user, Model model) {
		
		UserDTO login = userService.login(user.getEmail(),user.getPwd());
		
		if(login == null) {
			model.addAttribute("emsg","Invalid Credictals");
			return "index";// This should return to the index page if the login fails
	    }
		
	    // If login succeeded, check if the password has been updated
		if(login.getPwdUpdated().equals("YES")) {
	        // Display the dash-board page	    	 
	    	QuoteResponseDTO quotation = userService.getQuotation();	
	    	model.addAttribute("quote", quotation);
	    	return "dashboard";
	    	
	    }else {
	        // Display reset password page	    
	    	ResetPwdDTO resetPwd = new ResetPwdDTO();
            resetPwd.setEmail(login.getEmail());
	    	
	    	model.addAttribute("resetPwd",resetPwd);
			return "resetPwd";
	    } 
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		UserDTO userDto = new UserDTO();
		model.addAttribute("user",userDto);
		
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);
		
		return "register";
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody // it will directly response to the ui 
	public Map<Integer, String> getStates(@PathVariable Integer countryId){
//		return userService.getStates(countryId);
//		  Map<Integer, String> states = userService.getStates(countryId);
//		    System.out.println("Fetched states: " + states);
//		    return states;
		return userService.getStates(countryId);
	}
	
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCity(@PathVariable Integer stateId){
//		return userService.getCities(stateId);
//		Map<Integer, String> cities = userService.getCities(stateId);
//	    System.out.println("Fetched cities: " + cities);
//	    return cities;
		return userService.getCities(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserDTO user,Model model) {
		
		boolean emailUnique =  userService.isEmailUnique(user.getEmail());
		if(emailUnique) {
			boolean register =userService.register(user);
			if(register) {
				model.addAttribute("smsg", "Registation Success");
			}else {
				model.addAttribute("emsg", "Registation Failed ");
			}
		}else {
			model.addAttribute("emsg","Duplicate Email Found");
		}
		
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);
		
		return "register";
	}
	
	
	@PostMapping("/resetPwd")
	public String resetPwd(@ModelAttribute("resetPwd") ResetPwdDTO resetPwd, Model model) {
		
		UserDTO login = userService.login(resetPwd.getEmail(), resetPwd.getOldPwd());
		if (login == null) {
			model.addAttribute("emsg", "Old Pwd is incorrect");
			return "resetPwd";
		}
		
		if(resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd())) {
			userService.resetPwd(resetPwd);
			QuoteResponseDTO quatation = userService.getQuotation();	
	    	model.addAttribute("quote", quatation);
			return "dashboard";
		}else {
			model.addAttribute("emsg", "New Pwd And confrim Pwd Not Matching");
			return "resetPwd";
		}
	}
	
	
	@GetMapping("/getQuote")
	public String getQuote(Model model) {
		QuoteResponseDTO quotation = userService.getQuotation();
	    model.addAttribute("quote",quotation);
	    return "dashboard";
		
	}
	
	
	
	
}
