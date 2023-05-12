package com.aurora;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//Spring Boot 应用程序的启动类
@SpringBootApplication
@MapperScan("com.aurora.mapper")//指定MyBatis Mapper接口所在的包，自动扫描并创建实现类
public class AuroraSpringbootApplication {
    //运行Spring Boot应用程序
    public static void main(String[] args) {
        SpringApplication.run(AuroraSpringbootApplication.class, args);
    }

    /**
     定义一个RestTemplate Bean，用于向其他应用程序发送HTTP请求
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
