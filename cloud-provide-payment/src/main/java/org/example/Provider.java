package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.example.mapper")
@EnableDiscoveryClient
@RefreshScope
public class Provider {
    public static void main(String[] args) {
        SpringApplication.run(Provider.class, args);
    }
}