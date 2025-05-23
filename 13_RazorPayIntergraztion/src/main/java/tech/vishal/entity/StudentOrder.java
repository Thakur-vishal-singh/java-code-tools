package tech.vishal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Table
@Entity
@Data
public class StudentOrder {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int orderId;
	
	private String name;
	
	private String course;
	
	private String email;
	
	private String phno;
	
	private Integer amount;
	
	private String orderStatus;
	
	private String razorPayOrderId;
	
	
}
