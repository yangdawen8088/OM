package com.zero2oneit.mall.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11 15:49
 */
@SpringBootApplication
@MapperScan("com.zero2oneit.mall.generator.mapper")
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

}
