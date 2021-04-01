package com.fystock.bigdata.cloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@MapperScan(basePackages = "com.fystock.bigdata.cloud.mapper")
public class FYStockAPIApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(FYStockAPIApplication9003.class, args);
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
