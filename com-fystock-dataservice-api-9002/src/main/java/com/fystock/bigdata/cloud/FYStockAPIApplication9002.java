package com.fystock.bigdata.cloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class FYStockAPIApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(FYStockAPIApplication9002.class, args);
    }

    /**
     * Spring Cloud Alibaba Sentinel 注解支持的配置Bean
     *
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
