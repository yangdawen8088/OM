package com.zero2oneit.mall.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Description:商品服务启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.goods.mapper")
@EnableFeignClients(basePackages = "com.zero2oneit.mall.goods.feign")
public class GoodsApplication {

    public static void main(String[] args){
        SpringApplication.run(GoodsApplication.class, args);
    }

}