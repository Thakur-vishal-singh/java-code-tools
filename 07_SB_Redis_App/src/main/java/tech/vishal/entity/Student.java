package tech.vishal.entity;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("studentHash")
@Data
public class Student {

	private Integer id;
	private String name;
	
}
