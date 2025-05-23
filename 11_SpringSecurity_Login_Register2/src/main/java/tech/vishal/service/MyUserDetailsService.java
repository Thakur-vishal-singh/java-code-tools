package tech.vishal.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.vishal.entity.Customer;
import tech.vishal.repo.CustomerRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer c = customerRepo.findByUname(username);
		 
		return new User(c.getUname(), c.getPwd(), Collections.emptyList());
		
	}
	
}
