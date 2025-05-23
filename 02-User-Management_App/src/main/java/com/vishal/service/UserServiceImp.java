package com.vishal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vishal.dto.QuoteResponseDTO;
import com.vishal.dto.ResetPwdDTO;
import com.vishal.dto.UserDTO;
import com.vishal.entity.CityEntity;
import com.vishal.entity.CountryEntity;
import com.vishal.entity.StateEntity;
import com.vishal.entity.UserEntity;
import com.vishal.repo.CityRepo;
import com.vishal.repo.CountryRepo;
import com.vishal.repo.StateRepo;
import com.vishal.repo.UserRepo;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Service 
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo  userRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private EmailService emailService;
	
	
//	login page getting email and password 
	@Override
	public UserDTO login(String email, String Pwd) {

		UserEntity userEntity = userRepo.findByEmailAndPwd(email, Pwd);// here we will take the email and pwd
		
		if(userEntity!=null) { // if the email and password is not null then login 
			UserDTO dto = new UserDTO(); // getting the user DTO object
		    BeanUtils.copyProperties(userEntity, dto); // here we copy from entity 4 line above data to the dto object
			return dto;// return the dto object 
		}
		
		return null;
	}
	
	
	
// here we no need to add any parameter because we want all country 
	@Override
	public Map<Integer, String> getCountries() {

		// list of country we are getting here but we want to to return map so we will convert
		List<CountryEntity> list = countryRepo.findAll();
		
		// here Map to convert 
		Map<Integer, String> countryMap = new HashMap<>();
		
		// 
		list.forEach(country ->{
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	
//	 here we need the add the parameter to get the state names based on the country id 
	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<StateEntity> list =stateRepo.findByCountryCountryId(countryId);
		
		Map<Integer, String> statesMap = new HashMap<>();
		
		list.forEach(state ->{
			statesMap.put(state.getStateId(), state.getStateName());
		});
		return statesMap;
	}

	
//	 here we need the add the parameter to get the city names based on the country id 
	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<CityEntity> list = cityRepo.findByStateStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap<>();
		
		list.forEach(city ->{
			cityMap.put(city.getCityId(),city.getCityName());
		});
		
		return cityMap;
	}
	
	

	@Override
	public boolean isEmailUnique(String email) {
		return null == userRepo.findByEmail(email);
//		              || both do dame work but above one is less code 
//     UserEntity byEmail = userRepo.findByEmail(email); 
//       return byemail == null;    
	}

	@Override
	public boolean register(UserDTO userDto) {

		String randomPwd = getRandomPwd();
		userDto.setPwd(randomPwd);
		userDto.setPwdUpdated("NO");
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);
		
//  after this also not going to save in the table country because userEntity 
//  country,state,city id are Entity object type and in DTO there are Integer type so that is problem 
//	so before saving the data we have do the association mapping here means 
		

		CountryEntity country =
				countryRepo.findById(userDto.getCountryId()).get();
		entity.setCountry(country);
		
		StateEntity state = stateRepo.findById(userDto.getStateId()).get();
		entity.setState(state);
		
		CityEntity city = cityRepo.findById(userDto.getCityId()).get();
		entity.setCity(city);
		
		UserEntity savedUser = userRepo.save(entity);
		
		if(savedUser != null) {
			String subject ="Your Registration Success";
//			String body ="Your Account Pwd :"+userDto.getPwd();
//			                 || both do work same 
			String body = "your Account Pwd :"+randomPwd;
			return emailService.sendEmail(userDto.getEmail(),subject,body);
		}
		
		return savedUser != null;// if this null then false if not then true
	}

	
	
	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder pwd = new StringBuilder();

		Random rnd = new Random();

		while (pwd.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}

		return pwd.toString();
	}
	
	

	@Override
	public boolean resetPwd(ResetPwdDTO restPwdDto) {
        
		UserEntity entity = userRepo.findByEmail(restPwdDto.getEmail());
		
		entity.setPwd(restPwdDto.getNewPwd());
		entity.setPwdUpdated("YES");
		
		UserEntity save = userRepo.save(entity);
		
		return save != null;
	}

	@Override
	public QuoteResponseDTO getQuotation() {

//		String apiUrl = "https://dummyjson.com/quites/random";
		String apiUrl =    "https://dummyjson.com/quotes/random";

		RestTemplate rt = new RestTemplate();
		ResponseEntity<QuoteResponseDTO> forEntity = rt.getForEntity(apiUrl, QuoteResponseDTO.class);
		
		return forEntity.getBody();
	}

	
}
