package com.zero2oneit.mall.member.utils;

import com.zero2oneit.mall.member.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Description: JWT工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/26
 */
@Component
public class JwtUtils {

    @Autowired
    private JwtProperties properties;

    public final int calendarField = Calendar.DATE; // 设置单位

    /**
     * JWT生成Token.<br/>
     * JWT构成: header, payload, signature
     *
     * @param id ：会员id
     * @param type ：应用类型
     * @return
     */
    public String createJwtToken(String id, String type){
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, properties.getExpire());
        Date expiresDate = nowTime.getTime();

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("om")
                .setIssuedAt(iatDate)
                .setExpiration(expiresDate)
                .claim("id", id)
                .claim("type", type)
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();

        return JwtToken;
    }

}