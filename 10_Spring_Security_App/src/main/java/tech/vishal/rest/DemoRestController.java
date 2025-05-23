package tech.vishal.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	@GetMapping("/contact")
	public String getContactInfo() {
		return "call : +99 85 39 66 77";
	}
	
	
	@GetMapping("/msg")
	public String getMsg() {
		return "hello guys i hop you are doing well";
	}
	
	
	@GetMapping("/greet")
	public String getGreet() {
		return "Good moring guys";
	}
	
	
}
