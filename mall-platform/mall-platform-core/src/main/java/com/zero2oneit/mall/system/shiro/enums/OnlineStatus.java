package com.zero2oneit.mall.system.shiro.enums;

/**
 * 用户会话
 */
public enum OnlineStatus {
    /**
     * 用户状态
     */
    ON_LINE("在线"), OFF_LINE("离线");

    private final String info;

    OnlineStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
