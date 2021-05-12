package com.zero2oneit.mall.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:验证服务启动类
 *
 * @author yjj
 * @date 2021-02-23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SmsApplication {

    public static void main(String[] args){
        SpringApplication.run(SmsApplication.class, args);
    }

}
