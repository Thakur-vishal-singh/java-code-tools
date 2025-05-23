//package tech.vishal.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import tech.vishal.entity.Customer;
//import tech.vishal.repo.CustomerRepository;
//import tech.vishal.service.JwtService;
//
//@RestController
//public class CustomerRestController {
//
//     @Autowired
//	private CustomerRepository crepo;
//	
//     
//    @Autowired
//	private PasswordEncoder pwdEncoder;
//	
//    @Autowired
//	private AuthenticationManager authManager;
//	
//    @Autowired
//    private JwtService jwt;
//    
//    @GetMapping("/welcome")
//    public String welcome() {
//    	return "welcomepage";
//    }
//    
//    @PostMapping("/login")
//    public ResponseEntity<String> loginCheck(@RequestBody Customer c){
//    	UsernamePasswordAuthenticationToken token = 
//    			new UsernamePasswordAuthenticationToken(c.getUname(), c.getPwd());
//    	try {
//    		Authentication authenticate = authManager.authenticate(token);
//    		
//    		if(authenticate.isAuthenticated()) {
//    			String jwtToken = jwt.generateToken(c.getUname());
//    			return new ResponseEntity<>(jwtToken, HttpStatus.OK);
//    		}
//    		
//    	}catch(Exception e) {
//    		// logger
//    	}
//    	return new ResponseEntity<String>("invalid Credentails",HttpStatus.BAD_REQUEST);
//    }
//    
//
//    
//    
//    @PostMapping("/register")
//    public String registerCustomer(@RequestBody Customer customer) {
//
//    	// duplicate check
//    	
//    	String encodedPwd = pwdEncoder.encode(customer.getPwd());
//    	customer.setPwd(encodedPwd);
//    	
//    	crepo.save(customer);
//    	
//    	return "User Registered";
//    }
//    
//	
//}
//
//
//
//
//
//
//
//
//
package tech.vishal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.vishal.entity.Customer;
import tech.vishal.repo.CustomerRepository;
import tech.vishal.service.JwtService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerRepository crepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwt;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to ashokit";
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody Customer c) {
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(c.getUname(), c.getPwd());

		try {
			Authentication authenticate = authManager.authenticate(token);

			if (authenticate.isAuthenticated()) {
				String jwtToken = jwt.generateToken(c.getUname());
				return new ResponseEntity<>(jwtToken, HttpStatus.OK);
			}

		} catch (Exception e) {
			//logger
		}

		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/register")
	public String registerCustomer(@RequestBody Customer customer) {
		
		// duplicate check

		String encodedPwd = pwdEncoder.encode(customer.getPwd());
		customer.setPwd(encodedPwd);

		crepo.save(customer);

		return "User registered";
	}

}









//
//
//
//
//
//
//
//
//
//
//
//
//
////@Autowired
////private CustomerService customerService;
////
////@Autowired 
////private AuthenticationManager authManager;
////
////
////@PostMapping("/login")
////public ResponseEntity<String> login(@RequestBody Customer customer){
////	
////	UsernamePasswordAuthenticationToken token = new 
////			UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPwd());
////	
////	// verify login details valid or not 
////	  Authentication authenticate = authManager.authenticate(token);
////	
////	  boolean status = authenticate.isAuthenticated();
////	  
////	  if(status) {
////		  return new ResponseEntity<String>("welcome", HttpStatus.OK);
////		  
////	  }else {
////		  return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
////	  }
////  
////}
////
////
////@PostMapping("/register")
////public ResponseEntity<String> registerCsutomer(@RequestBody Customer customer){
////	boolean status = customerService.SaveCustomer(customer);
////	if(status) {
////		return new ResponseEntity<>("Success", HttpStatus.CREATED);
////	}else {
////		return new  ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);// 500 NUMBER ERROR GIVE IF IT COME IN ELSE BLOCK
////	}
////}