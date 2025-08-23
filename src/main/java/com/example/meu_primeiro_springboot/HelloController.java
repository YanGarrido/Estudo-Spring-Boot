package com.example.meu_primeiro_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // stereotype annotation, in this case,our class is a web @Controller
@RequestMapping("/api") // provides routing information
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "Olá, mundo !! Bem vindo ao Spring Boot";
  }
}
