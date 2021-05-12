package com.zero2oneit.mall.member.config;

import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.common.utils.json.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtProperties properties;

    /**
     * 间隔时间，单位:秒 默认10秒
     * <p>
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    private final int intervalTime = 5;

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request) throws Exception {
        // 请求地址（作为存放value值）
        String url = request.getRequestURI();
        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)){
            String id = getId(token);
            String uri = redisTemplate.opsForValue().get("om:form:repeat:" + id + url);
            if(StringUtils.isNotBlank(uri)){
                return true;
            }
            redisTemplate.opsForValue().set("om:form:repeat:" + id + url, url, intervalTime, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * 根据token获取会员id
     * @param token
     * @return
     */
    public String getId(String token) {
        if(org.springframework.util.StringUtils.isEmpty(token)){
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

}
