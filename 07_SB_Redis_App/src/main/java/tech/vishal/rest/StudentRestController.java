package tech.vishal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.vishal.entity.Student;
import tech.vishal.repo.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository srepo;
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {
		srepo.save(student);
		return "Student Saved";
	}
	
	
	@GetMapping("/students")
	public Iterable<Student> getAllStudent(){
		return srepo.findAll();
	}
	
	@Cacheable(value="students", key="#id")
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable Integer id) {
		return srepo.findById(id).get();
	}
	
	
	
}
