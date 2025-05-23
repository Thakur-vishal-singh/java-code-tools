package tech.vishal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.vishal.service.MsgService;

@RestController
public class MsgRestController {

	@Autowired
	private MsgService msgService;
	
	@GetMapping("/welcome")
	public String getWelcome() {
		System.out.println(msgService.getClass().getName());
		String welcomeMsg = msgService.getWelcomeMsg();
		welcomeMsg = welcomeMsg.toUpperCase();
		return welcomeMsg;
	}
	
	
	@GetMapping("/greet")
	public String getGreet() {
		System.out.println(msgService.getClass().getName());
		String greetMsg = msgService.getGreetMsg();
		greetMsg = greetMsg.toLowerCase();
		return greetMsg;
	}
	
}
