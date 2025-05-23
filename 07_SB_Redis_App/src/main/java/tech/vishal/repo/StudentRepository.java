package tech.vishal.repo;

import org.springframework.data.repository.CrudRepository;

import tech.vishal.entity.Student;


public interface StudentRepository extends CrudRepository<Student, Integer> {

	
	
}
