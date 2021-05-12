package com.zero2oneit.mall.gateway.test;

import com.zero2oneit.mall.gateway.config.JwtProperties;
import com.zero2oneit.mall.gateway.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/3/2
 */
@SpringBootTest
public class GatewayTest {

    @Autowired
    JwtProperties prop;
    @Autowired
    JwtUtils jwtUtils;

    @Test
    public void test() throws Exception{
        System.out.println("prop.getExpireTime() = " + prop.getExpire());

        /*String token = jwtUtils.createToken(1L);

        System.out.println("token = " + token);

        System.out.println(jwtUtils.verifyToken(token));*/
    }


}