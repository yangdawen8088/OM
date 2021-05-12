package com.zero2oneit.mall.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description: OSS启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class, args);
    }

}
