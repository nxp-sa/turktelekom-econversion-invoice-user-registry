package com.turktelekom.econversion.invoice_user_registry.infra.persistence.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;

@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    String connectionString;

    @Bean
    public MongoTemplate mongoTemplate() {
        ConnectionString mongoConnectionString = new ConnectionString(connectionString);
        MongoClient mongoClient = MongoClients.create(mongoConnectionString);

        var database = mongoConnectionString.getDatabase();
        return new MongoTemplate(mongoClient, database);
    }
}
