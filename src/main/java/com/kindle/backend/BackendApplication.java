package com.kindle.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendApplication {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "Hello World";
  }

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}
