package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderComsumerNacos {
    public static void main(String[] args) {
        SpringApplication.run(OrderComsumerNacos.class, args);
    }
}