package com.dinhb.microservices.publisherservice.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublishService {
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedRate = 2000)
	public void publishMessageAsync(String message) {
		log.info("Sending message: {}", message);
		kafkaTemplate.send("notification-topic", message)
				.whenComplete((result, ex) -> {
					if (ex != null) {
						onFailure(ex);
					} else {
						onSuccess(result);
					}
				});
	}

	private void onSuccess(SendResult<String, String> result) {
		log.info("Receive new metadata. \n"
						+ "Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
				result.getRecordMetadata().topic(),
				result.getRecordMetadata().partition(),
				result.getRecordMetadata().offset(),
				result.getRecordMetadata().timestamp());
	}

	private void onFailure(Throwable ex) {
		log.error("Unable to send message. \n"
				+ ex.getMessage());
	}
}
