package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController //Steoreotype for RESTful controller
@SpringBootApplication //meta annotation often placed on your main class (main class also know as configuration class)
//@EnableAutoConfiguration tells Spring Boot how you want to configure Spring, based on the jar dependencies.

public class DemoApplication {
  @RequestMapping("/") //provides “routing” information. 
  String home() {
    return "Hello World!";
  }
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}