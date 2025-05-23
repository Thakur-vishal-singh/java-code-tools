package com.vishal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer> {

	
//	public Optional findById(Integer userId);
	
	public List<StateEntity> findByCountryCountryId(Integer countryId);
	
//	public Optional<StateEntity> findById(String id);
//	
//	public List<StateEntity> findAllById(Iterable<Integer> ids);
	
}
