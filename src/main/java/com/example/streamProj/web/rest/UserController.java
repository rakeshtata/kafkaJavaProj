package com.example.streamProj.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.streamProj.web.kafka.KProducer;
import com.example.streamProj.web.model.User;

@RestController
public class UserController {
	KProducer producer = new KProducer();

	public UserController() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello World";
	}
	
	@PostMapping(path = "users", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody User newUser) {
			//KProducer producer = new KProducer();
			try {
				//producer.produce(newUser);
				producer.sendMessage(newUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	   
	}
	
	
}
