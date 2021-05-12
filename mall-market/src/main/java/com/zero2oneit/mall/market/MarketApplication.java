package com.zero2oneit.mall.market;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-01-25
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.market.mapper")
public class MarketApplication {

    public static void main(String[] args){
        SpringApplication.run(MarketApplication.class, args);
    }

}