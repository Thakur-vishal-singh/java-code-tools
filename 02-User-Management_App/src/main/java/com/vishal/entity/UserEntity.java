package com.vishal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_master")
@Setter
@Getter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer userId;
	private String  name;
	private String  email;
	private String  pwd;
	private String  pwdUpdated;
	private Long    phno;
	
	
	@ManyToOne 
	@JoinColumn(name="country_id")
     private CountryEntity country;
     
	 @ManyToOne
	 @JoinColumn(name="city_id")
     private CityEntity city;
	 
	 @ManyToOne
	 @JoinColumn(name="state_Id")
     private StateEntity state;
	
}
