package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeRestController {
	
	@GetMapping("/welcome")
	public String getMsg() {
		return "welcome to ashok it";
	}	
}
