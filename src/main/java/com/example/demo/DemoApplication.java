package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
//------

@RestController //Steoreotype for RESTful controller
@SpringBootApplication //meta annotation often placed on your main class (main class also know as configuration class)
//@EnableAutoConfiguration tells Spring Boot how you want to configure Spring, based on the jar dependencies.

public class DemoApplication {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


  @RequestMapping("/") 
  String home() {
    return "Esto es del primer tutorial de Spring Boot. Pero ya hice el segundo, wii";
  }
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
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