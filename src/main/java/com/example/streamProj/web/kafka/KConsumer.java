package com.example.streamProj.web.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.example.streamProj.web.repo.MongoRepo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.example.streamProj.web.model.User;


public class KConsumer {
	static String TOPIC ="topic1";
	private static Consumer<Long, String> createConsumer() {
	      final Properties props = new Properties();

	     
	      
	    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumer");
	    props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

	      // Create the consumer using props.
	      final Consumer<Long, String> consumer =
	                                  new KafkaConsumer<>(props);

	      // Subscribe to the topic.
	      consumer.subscribe(Collections.singletonList(TOPIC));
	      return consumer;
	  }
	
	public static void runConsumer() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        MongoRepo repo = new MongoRepo();

        final int giveUp = 10;   int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(10);

//            if (consumerRecords.count()==0) {
//                noRecordsCount++;
//                if (noRecordsCount > giveUp) break;
//                else continue;
//            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
//                JsonObject object = (JsonObject) parser.parse(record.value());// response will be the json String
//                User user = gson.fromJson(object, User.class); 
//                repo.insertDoc(user);
                
            });

            consumer.commitAsync();
        }
//        consumer.close();
//        System.out.println("DONE");
    }
}
