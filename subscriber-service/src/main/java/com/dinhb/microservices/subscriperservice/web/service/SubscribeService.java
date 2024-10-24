package com.dinhb.microservices.subscriperservice.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {
	private final KafkaTemplate<String, String> kafkaTemplate;
}
