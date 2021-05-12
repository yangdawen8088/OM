package com.zero2oneit.mall.system.log.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * 平台登录日志
 */
@Data
public class FlatformLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    private Long id;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登出时间
     */
    private Date logoutTime;

    /**
     * 回话id
     */
    private String sessionId;

    /**
     * ip登录地址：需要第三方接口接入
     */
    private String ipAddress;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 类型：1-web 2-android 3-ios
     */
    private Integer loginType;

    public FlatformLoginLog() {
    }

}
