package tech.vishal.service;

import org.springframework.stereotype.Service;

@Service
public class MsgService {

	public String getWelcomeMsg() {
		
		return "Welcome to Ashok it";
	}
	
	public String getGreetMsg() {
		
		return "Good Moring";
	}
	
}
