package tech.vishal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tech.vishal.constants.AppConstants;
import tech.vishal.model.Order;

@Service
public class OrderService {

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public String addMsg(Order order){
		
		// publish msg to kafka topic 
		kafkaTemplate.send(AppConstants.TOPIC, order);
		
		return "Msg Publichsed to Kafka Topic";
	}
	
	
}
