package com.example.demo.consumingRestfulWebService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //tells Jackson to ignore any attributes not listed in the class
public record Quote(String type, Value value) { } //record is a new feature in Java 14. It is a class that is designed to store data.