package com.example.streamProj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import com.example.streamProj.web.kafka.KConsumer;

@SpringBootApplication
public class StreamProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamProjApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void kafkaStartup() {
		
	    System.out.println("hello world, I have just started up");
	    try {
			KConsumer.runConsumer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
