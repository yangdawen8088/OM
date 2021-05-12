package com.zero2oneit.mall.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-23
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.business.mapper")
public class BusinessApplication {

    public static void main(String[] args){
        SpringApplication.run(BusinessApplication.class, args);
    }

}