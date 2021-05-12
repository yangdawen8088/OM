package com.zero2oneit.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description:会员服务启动类
 *
 * @author yjj
 * @date 2021-02-05
 */
@EnableFeignClients(basePackages = "com.zero2oneit.mall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.member.mapper")
public class MemberApplication {

    public static void main(String[] args){
        SpringApplication.run(MemberApplication.class, args);
    }

}
