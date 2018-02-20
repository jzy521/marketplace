package com.intuit.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.marketplace.kafka.IntuitProducer;


@RestController
@ComponentScan("com.intuit.marketplace")
@EnableAutoConfiguration
public class App{


	@Autowired
	private IntuitProducer producer;
	
	private static final String INTUITMARKETTOPIC = "helloworld.t";


    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);

    }
    
    @GetMapping("/msg")
	public void getKafka() throws InterruptedException {
      	producer.send(INTUITMARKETTOPIC, "Welcome to Intuit Marketplace!");
	}
    
 
}
