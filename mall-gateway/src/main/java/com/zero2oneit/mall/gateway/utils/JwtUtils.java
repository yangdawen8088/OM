package com.zero2oneit.mall.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.zero2oneit.mall.gateway.config.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     *
     * JWT构成: header, payload, signature
     *
     * @param id
     *            登录成功后用户user_id, 参数user_id不可传空
     */
    public String createToken(Long id) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, properties.getExpire());
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Tg") // payload
                .withClaim("aud", "APP")
                .withClaim("id", null == id ? null : id.toString())
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(properties.getSecret())); // signature

        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Map<String, Claim> decryptToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(properties.getSecret())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }

    /**
     * 验证Token
     *
     * @param token
     * @return user_id
     */
    public boolean verifyToken(String token) {
        Map<String, Claim> claims = decryptToken(token);
        Claim user_id_claim = claims.get("id");
        return user_id_claim != null && StringUtils.isNotEmpty(user_id_claim.asString());
    }

    /**
     * 根据Token获取id
     *
     * @param token
     * @return user_id
     */
    public Long getId(String token) {
        Map<String, Claim> claims = decryptToken(token);
        Claim user_id_claim = claims.get("id");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return Long.valueOf(user_id_claim.asString());
    }

}