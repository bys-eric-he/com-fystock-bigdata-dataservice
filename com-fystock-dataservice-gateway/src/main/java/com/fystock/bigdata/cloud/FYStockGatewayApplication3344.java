package com.fystock.bigdata.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
public class FYStockGatewayApplication3344 {
    public static void main(String[] args) {
        SpringApplication.run(FYStockGatewayApplication3344.class, args);
    }
}
