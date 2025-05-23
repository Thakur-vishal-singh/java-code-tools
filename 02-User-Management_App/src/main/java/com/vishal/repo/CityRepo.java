package com.vishal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entity.CityEntity;

//JpaRepository
public interface CityRepo extends JpaRepository<CityEntity, Integer> {
		
	public List<CityEntity> findByStateStateId(Integer stateId);
	
	
}
