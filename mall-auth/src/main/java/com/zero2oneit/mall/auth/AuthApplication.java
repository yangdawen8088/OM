package com.zero2oneit.mall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description:验证服务启动类
 *
 * @author yjj
 * @date 2021-02-23
 */
@EnableFeignClients(basePackages = "com.zero2oneit.mall.auth.feign.*")
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthApplication.class, args);
    }

}
