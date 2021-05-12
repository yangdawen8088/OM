package com.zero2oneit.mall.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 短信属性
 *
 * @author yjj
 * @date 2020/7/2 11:11
 */
@Data
@ConfigurationProperties(prefix = "mall.sms")
public class SmsProperties {

    //阿里AccessKeyId
    private String accessKeyId ;

    //阿里AccessKeySecret
    private String accessKeySecret;

}
