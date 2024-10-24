package com.dinhb.microservices.subscriperservice.web.controller;

import com.dinhb.microservices.subscriperservice.web.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscribeController {
	private final SubscribeService subscribeService;

	@GetMapping("/subscribe")
	@KafkaListener(topics = "notification-topic", groupId = "notification-group")
	public void subscribe(ConsumerRecord<String, String> message) {
		log.info("Key: {} | Value: {} ", message.key(), message.value());
		log.info("Partition: {} | Offset: {}", message.partition(), message.offset());
	}
}
