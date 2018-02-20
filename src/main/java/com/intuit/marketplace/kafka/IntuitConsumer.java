package com.intuit.marketplace.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class IntuitConsumer {

	   private Logger logger = LoggerFactory.getLogger(this.getClass());

	   @KafkaListener(topics = "${kafka.topic.helloworld}")
	   public void consume(String payload) {
		   logger.info("received payload : " + payload);
	   }
}
