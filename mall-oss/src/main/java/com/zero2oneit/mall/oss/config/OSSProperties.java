package com.zero2oneit.mall.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: OSS属性信息
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/26
 */
@Data
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OSSProperties {

    private String bucketname;

    private String endpoint;

    private String keyid;

    private String keysecret;

}
