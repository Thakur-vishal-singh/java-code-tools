package tech.vishal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;
import tech.vishal.service.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private CustomerService customerService;
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}// this is for the decripet the password 
	
	@Bean    // because we wrote this that is reason we are not getting the default password 
	public DaoAuthenticationProvider authProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();// it use to load the customer record 
		
		authProvider.setPasswordEncoder(pwdEncoder());// it will call the pwdEncoder method we use this because it need to compare the database password and given password
		authProvider.setUserDetailsService(customerService);// it will set the customer-details so it will first go inside the customerSerivce class and that extends UserDetailsService so it will call the customer details all and set it 
		
		return authProvider;// it will return the 
		
	}// this is for the load the record and give to auth manager 
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}// it will check user is valid or not 
	
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain  security(HttpSecurity  http) {
		
		http.authorizeHttpRequests( req-> {
			
			req.requestMatchers("/register", "/login")
			.permitAll()        //register
			.anyRequest()
			.authenticated();
		});
		
		return http.csrf().disable().build();
	}
	
	
}
