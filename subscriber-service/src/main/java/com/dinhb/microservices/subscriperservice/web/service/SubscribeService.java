package com.dinhb.microservices.subscriperservice.web.service;

import com.dinhb.microservices.subscriperservice.web.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {
	@KafkaListener(topics = "notification-topic", groupId = "notification-group")
	private Notification consume(Notification message) {
		log.info("Consumed message: {}", message);
		return message;
	}
}
