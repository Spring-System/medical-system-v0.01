package com.dinhb.microservices.publisherservice.web.controller;

import com.dinhb.microservices.publisherservice.web.entity.Notification;
import com.dinhb.microservices.publisherservice.web.service.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublishController {
	private final PublishService publishService;

	@GetMapping("/publish")
	public ResponseEntity<Boolean> sendMessage(
			@RequestBody Notification notification) {
		try {
			publishService.publishMessageAsync("notification-topic", notification);
			return ResponseEntity.ok(Boolean.TRUE);
		} catch (Exception e) {
			return ResponseEntity.ok(Boolean.FALSE);
		}
	}
}
