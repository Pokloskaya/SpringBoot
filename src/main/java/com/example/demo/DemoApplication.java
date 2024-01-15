package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.demo.storage.StorageProperties;
import com.example.demo.storage.StorageService;

import com.example.demo.consumingRestfulWebService.Quote;

//----------------
@SpringBootApplication //meta annotation often placed on your main class (main class also know as configuration class)
//@EnableAutoConfiguration tells Spring Boot how you want to configure Spring, based on the jar dependencies.

@EnableConfigurationProperties(StorageProperties.class)

public class DemoApplication {

  	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  	public static void main(String[] args) {
    	SpringApplication.run(DemoApplication.class, args);
  	}

  	@Bean
    CommandLineRunner init(StorageService storageService) {
	return (args) -> {
		storageService.deleteAll();
		storageService.init();
	  };
  	}

  	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

  	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://localhost:8081/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}