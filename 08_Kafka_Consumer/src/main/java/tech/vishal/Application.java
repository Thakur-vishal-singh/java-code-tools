package tech.vishal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import tech.vishal.constants.AppConstants;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@KafkaListener(topics = AppConstants.TOPIC, groupId ="group_ashokit_orders")
	public void counsumeOrderMsg(String order) {
		System.out.println("** Msg From Kafka **");
		System.out.println(order);
		//logic 
	}

}
