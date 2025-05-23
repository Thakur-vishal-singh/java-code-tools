package tech.vishal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tech.vishal.rest.MsgRestController;
import tech.vishal.service.MsgService;

@WebMvcTest(value= MsgRestController.class)
public class MsgRestControllerTest {

	@MockitoBean
	private MsgService msgService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testWelcomeMsg() throws Exception {

		when(msgService.getWelcomeMsg()).thenReturn("welcome");
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome");// create the request
		
		MvcResult mvcResult =  mockMvc.perform(reqBuilder).andReturn(); // send the request 
		
		MockHttpServletResponse response = mvcResult.getResponse(); // it is used to get the request  
		
//	    String actual= response.getContentAsString();
//		String expected = "Welcome to ashokit IT";
	    
		int actual = response.getStatus();
		int expected = 200;
		
	    // validate content using assertions
	    
	    assertEquals(expected, actual);
		
	}
	
	
	@Test
	public void testGreetMsg() throws Exception {
		
          when(msgService.getGreetMsg()).thenReturn("good afternoon");
          
          MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/greet");
          
         MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
          
         MockHttpServletResponse response = mvcResult.getResponse();
         
         String actual = response.getContentAsString();
         
         String expected = "good afternoon";
                         
         assertEquals(expected, actual);
		
		
	}
	
	
	
}




//package tech.vishal.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import tech.vishal.rest.MsgRestController;
//import tech.vishal.service.MsgService;
//
//@WebMvcTest(value = MsgRestController.class)
//public class MsgRestControllerTest {
//
//    @MockBean
//    private MsgService msgService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testWelcomeMsg() throws Exception {
//        // Define mock behavior
//        when(msgService.getWelcomeMsg()).thenReturn("Welcome to Ashok IT");
//
//        // Create request
//        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome");
//
//        // Perform request
//        MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//
//        // Validate response content
////        String actualContent = response.getContentAsString();
////        String expectedContent = "Welcome to Ashok IT";
////        assertEquals(expectedContent, actualContent);
//
//        // Validate response status
//        int actualStatus = response.getStatus();
//        int expectedStatus = 200;
//        assertEquals(expectedStatus, actualStatus);
//    }
//}
