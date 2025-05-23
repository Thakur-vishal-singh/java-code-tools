package tech.vishal.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import tech.vishal.dao.User;
import tech.vishal.dao.UserDao;
import tech.vishal.service.UserService;

@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
public class UserServiceTest {

	@MockitoBean
	private UserDao userDao;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void testSaveUser() {
		
		when(userDao.save(any())).thenReturn(true);
		User user = new User();
		user.setId(101);
		user.setName("vishal");
		
		boolean actual = userService.saveUser(user);
		
		assertTrue(actual);
		
	}
	
}
