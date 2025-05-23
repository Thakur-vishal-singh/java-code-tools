package tech.vishal.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.razorpay.RazorpayClient;

import lombok.SneakyThrows;
import tech.vishal.entity.StudentOrder;
import tech.vishal.repo.StudentRepo;

public class StuddentService {

	@Autowired
	private StudentRepo studentRepo;	
	
	
	@Value("${razorpay.key.id}")// it is used to read the properties from the application.properties
	private String razorPayKey;
	
	@Value("$razorpay.secert.key")
	private String razorPaySecret;
	
	
	private RazorpayClient client;  // we added the razor pay repository so we can add this class 
	
	@SneakyThrows
	public StudentOrder CreateOrder(StudentOrder order) {
		
		JSONObject orderRequest = new JSONObject();
		
	
		orderRequest.put("amount", order.getAmount() * 100); // always take the data in form of paisa only rupe to paisa 
		orderRequest.put("receipet", "INR");  // take amount in Indian rupess
		orderRequest.put("receipet", order.getEmail());		// this is data we will send to the razor pay for create the order 
		
		this.client = new RazorpayClient(razorPayKey, razorPaySecret);
		
		client.orders.create(null);// this create method which will take only JSON object 
		
		return null;
	}
	
	
}
