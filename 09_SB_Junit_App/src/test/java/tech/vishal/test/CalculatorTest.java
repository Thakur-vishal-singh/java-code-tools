package tech.vishal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tech.vishal.beans.Calculator;

@SpringBootTest
public class CalculatorTest {

	@Test
	public void testAdd() {
		
		Calculator c = new Calculator();
		int actualResult = c.add(10, 20);
		
		int expectedResult = 30;
		
		//Assertions is to check the both are same or not expected result is matching with actual result or not
		
		assertEquals(expectedResult, actualResult);
		// we expecting the 30 and we got the 31 that is error  
	}
	
	
	@Test
	public void testMul() {
		
		Calculator c = new Calculator();
        int actualResult = c.mul(10, 20);
        int expectedResult = 200;
        
        assertEquals(expectedResult, actualResult);
		
		
	}
	
}

