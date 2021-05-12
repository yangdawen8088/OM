package com.zero2oneit.mall.member.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "mall.jwt")
@Component
public class JwtProperties {

    private String secret;// token秘钥，请勿泄露，请勿随便修改

    private Integer expire;// 过期时间(单位：天)

}
