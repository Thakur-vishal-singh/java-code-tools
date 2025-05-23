package tech.vishal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.vishal.dao.User;
import tech.vishal.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		boolean saveUser = userService.saveUser(user);
		if(saveUser) {
			sendEmail();
			return new ResponseEntity<>("user saved",HttpStatus.CREATED);
		}else {
		    return new ResponseEntity<String>("Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void sendEmail() {
		// logics
	}
}
