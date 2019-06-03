package com.herokuapp.desafioamedigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioAmeDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioAmeDigitalApplication.class, args);
    }
}

