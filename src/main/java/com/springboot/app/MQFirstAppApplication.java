package com.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJms
public class MQFirstAppApplication {
	
	 @Autowired
     private JmsTemplate jmsTemplate;
	 
	 @GetMapping("send")
	 String send(){
	     try{
	         jmsTemplate.convertAndSend("Q", "MQ Client");
	         return "OK";
	     }catch(JmsException ex){
	         ex.printStackTrace();
	         return "FAIL";
	     }
	 }
	 
	 @GetMapping("recv")
	 String recv(){
	     try{
	         return jmsTemplate.receiveAndConvert("Q").toString();
	     }catch(JmsException ex){
	         ex.printStackTrace();
	         return "FAIL";
	     }
	 }

	public static void main(String[] args) {
		SpringApplication.run(MQFirstAppApplication.class, args);
	}

}
