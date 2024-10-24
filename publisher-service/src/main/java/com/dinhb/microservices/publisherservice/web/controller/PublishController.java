package com.dinhb.microservices.publisherservice.web.controller;

import com.dinhb.microservices.publisherservice.web.entity.Notification;
import com.dinhb.microservices.publisherservice.web.service.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class PublishController {
	private final PublishService publishService;
	@GetMapping("/publish")
	public void sendMessage(String message) {
		publishService.publishMessageAsync(message);
	}
}
