package tech.vishal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	
		// use spring httpheader
		HttpHeaders header = new HttpHeaders();
//	Authorization Basic encodedCredentials (internal it will go like this when we setBaicAuth postman also go like this way )
		header.setBasicAuth("vishal","vishal");
		HttpEntity<String> reqEntity = new HttpEntity<>(header);
		
		
		
		RestTemplate rt = new RestTemplate();
		
		String apiUrl="http://localhost:8081/msg";
		
//		ResponseEntity<String> forEntity = rt.getForEntity(apiUrl, String.class); // we can't call directly anything 
	
		ResponseEntity<String> resEntity = rt.exchange(apiUrl, HttpMethod.GET, reqEntity, String.class);
		
		System.out.println(resEntity.getBody());
		
		
	}

}
