package com.vishal.service;

//import java.util.List;
import java.util.Map;

import com.vishal.dto.QuoteResponseDTO;
import com.vishal.dto.ResetPwdDTO;
import com.vishal.dto.UserDTO;
//import com.vishal.entity.CountryEntity;

public interface UserService {

	
// 1)  this is for login purpose only
	
	
 // public UserDTO login(UserDTO userDto);
      //         || you can use anything 1) object parameter like UserDTO userDto or 2) String email,String pwd;
	public UserDTO login(String email,String Pwd);
    //	         || //we are using here return type as the UserDTO because we need to check the is user updated the password or not when he login first time
   //  public boolean login(String email,String pwd);
  //  we ````are not using boolean here because we need to check the user update password after login there next logic with boolean we can't do that
 //  best what i see use object only because it work both same and give advantage 
	
	
// 2)  this method for the register purpose only
	public Map<Integer, String> getCountries();
//	       || we can use both but we don't need expose the entity class because all table data can access with that because we don't use List<CountryEntity>
//	public List<CountryEntity> getCounties();
	
	public Map<Integer, String> getStates(Integer CountryId);
	
	
	public Map<Integer,String> getCities(Integer StateId);;
	
	public boolean isEmailUnique(String email);
	
	public boolean register(UserDTO userDto);
	

// 3)	rest password dto here we are doing work
	public boolean resetPwd(ResetPwdDTO restPwdDto);	
	
	
// 4)   Random quote response from the third party website 
	public QuoteResponseDTO getQuotation();
	
	
	
	
	
}
