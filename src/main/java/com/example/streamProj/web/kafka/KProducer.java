package com.example.streamProj.web.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.streams.HeaderEnricher.Container;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.example.streamProj.web.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KProducer {
	
	final Properties props = new Properties();
	static String TOPIC ="topic1";

	public KProducer() {
		super();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ProducerConfig.ACKS_CONFIG,"all");
		props.setProperty(ProducerConfig.RETRIES_CONFIG,"0");
		props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
		props.setProperty(ProducerConfig.LINGER_MS_CONFIG,"1");
		props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		Thread.currentThread().setContextClassLoader(null);
	}
	public void produce(User user) throws InterruptedException, ExecutionException {
		Producer<String,String> producer = new KafkaProducer<String,String>(props);
		 ObjectMapper mapper = new ObjectMapper();
		try { 
			String json = mapper.writeValueAsString(user);
			producer.send(new ProducerRecord<String, String>(TOPIC, json));
			
		} catch (JsonProcessingException e) {
            e.printStackTrace();
		}
		
	
		producer.close();
	}
	
	
}
