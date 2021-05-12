package com.zero2oneit.mall;

import com.zero2oneit.mall.system.PlatformCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Description: 平台配置启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/13
 */
@EnableFeignClients(basePackages = "com.zero2oneit.mall.feign.*")
@EnableDiscoveryClient
@SpringBootApplication
@Import(PlatformCoreApplication.class)
public class PlatformMainApplication {

    public static void main(String[] args){
        SpringApplication.run(PlatformMainApplication.class, args);
    }

}
