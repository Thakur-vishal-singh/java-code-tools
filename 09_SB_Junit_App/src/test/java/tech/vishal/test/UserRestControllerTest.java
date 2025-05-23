package tech.vishal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.vishal.dao.User;
import tech.vishal.rest.UserRestController;
import tech.vishal.service.UserService;

@WebMvcTest(value=UserRestController.class)
public class UserRestControllerTest {

	@MockitoBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSaveUser_Tc1() throws Exception {
		
		User user = new User();
		user.setId(101);
		user.setName("vishal");
		
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		
		when(userService.saveUser(any())).thenReturn(true);

// when value is true it 201
//  400 error means user site mistake
//	when the value is false it should return 500	
		
		MockHttpServletRequestBuilder reqBuilder =
				MockMvcRequestBuilders.post("/user")
				                      .content(userJson)
				                      .contentType("application/json");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int actual = response.getStatus();
		
		int expected = 201;
		
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	public void testSaveUser_Tc2() throws Exception {
		
		User user = new User();
		user.setId(101);
		user.setName("vishal");
		
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		
		when(userService.saveUser(any())).thenReturn(false);
		
//  400 error means user site mistake
		
		
		MockHttpServletRequestBuilder reqBuilder =
				MockMvcRequestBuilders.post("/user")
				                      .content(userJson)
				                      .contentType("application/json");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int actual = response.getStatus();
		
		int expected = 500;
		
		assertEquals(expected, actual);
		
	}
	
	
	
	
}
