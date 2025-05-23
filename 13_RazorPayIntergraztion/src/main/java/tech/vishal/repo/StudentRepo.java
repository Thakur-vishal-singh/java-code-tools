package tech.vishal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.vishal.entity.StudentOrder;

public interface StudentRepo extends JpaRepository<StudentOrder, Integer>{
	
	
	
	
}



