package com.example.streamProj.web.repo;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.streamProj.web.model.User;
import com.example.streamProj.web.repo.MongoConfig;

public class MongoRepo {
	public void insertDoc(User user) {
		MongoConfig mongo = new MongoConfig();
		MongoTemplate template;
		try {
			template = mongo.mongoTemplate();
			template.insert(user,user.getUserId().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
