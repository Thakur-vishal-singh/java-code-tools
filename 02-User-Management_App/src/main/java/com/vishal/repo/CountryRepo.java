package com.vishal.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {

//	public Optional<CountryEntity> findById(String id);
////	public CountryEntity findByCountry(String countryName);
//	
//	public List<CountryEntity> findAllById(Iterable<Integer> ids);
//	
}
