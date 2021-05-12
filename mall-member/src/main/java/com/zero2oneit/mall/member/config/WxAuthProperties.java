package com.zero2oneit.mall.member.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wx.auth")
public class WxAuthProperties {

    private String appletId;

    private String appletSecret;

    private String appUrl;//验证请求链接

}
