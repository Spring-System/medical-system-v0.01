package com.dinhb.microservices.subscriperservice.web.controller;

import com.dinhb.microservices.subscriperservice.web.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscribeController {
	private final SubscribeService subscribeService;

	@GetMapping("/subscribe")
	public void subscribe() {
		log.info("Key: {} | Value: {} ", "key", "value");
		log.info("Partition: {} | Offset: {}", "partition", "offset");
	}
}
