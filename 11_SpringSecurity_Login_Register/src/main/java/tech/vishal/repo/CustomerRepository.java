package tech.vishal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.vishal.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Customer findByEmail(String email);
	
	
}
