package com.intuit.marketplace.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IntuitProducer {

	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	  @Autowired
	  private KafkaTemplate<String, String> kafkaTemplate;
	  
	  public void send(String topic, String payload) {
		  logger.info("sending payload : ", payload, topic);
	      kafkaTemplate.send(topic, payload);
	  }
}
