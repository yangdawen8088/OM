package com.zero2oneit.mall.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 *
 * <p>微信配置 Bean</p>
 *
 * @author Javen
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.auth")
public class WxAuthProperties {
    private String url;
    private String urlParam;
    private String appletId;
    private String appletSecret;

    private Map<String ,String > appIdMap;
    private Map<String ,String > appSecretMap;

    private String weChatClientId;
    private String weChatAppSecret;
    private String weChatRedirectUri;

    private String openClientId;
    private String openAppSecret;
    private String openRedirectUri;
}
