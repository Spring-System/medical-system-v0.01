package com.dinhb.microservices.publisherservice.config;

import com.dinhb.microservices.publisherservice.web.entity.Notification;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
	@Value("${spring.kafka.bootstrap-servers:localhost:9092}")
	String bootstrapServers;
	@Bean
	public ProducerFactory<String, Notification> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapServers);
		configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
		configProps.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	//Generate topic if not exist
	@Bean
	public NewTopic notificationTopic() {
		return new NewTopic("notification-topic", 1, (short) 1);
	}

	@Bean
	public KafkaTemplate<String, Notification> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
