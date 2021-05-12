package com.zero2oneit.mall.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.log.mapper")
public class LogApplication {

    public static void main(String[] args){
        SpringApplication.run(LogApplication.class, args);
    }

}