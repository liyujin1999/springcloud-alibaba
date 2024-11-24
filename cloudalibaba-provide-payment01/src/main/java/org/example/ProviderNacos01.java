package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderNacos01 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderNacos01.class, args);
    }
}