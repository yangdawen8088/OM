package com.zero2oneit.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author Lee
 * @create 2021/3/17
 * @description: 启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args){
        SpringApplication.run(SearchApplication.class, args);
    }

}
