package tech.vishal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.vishal.dao.User;
import tech.vishal.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean saveUser(User user) {
	return userDao.save(user);
	}
	
	private String generateRandomPwd(int length) {
		return null;
	}
	
}
