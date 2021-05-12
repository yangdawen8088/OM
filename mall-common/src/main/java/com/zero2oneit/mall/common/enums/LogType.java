package com.zero2oneit.mall.common.enums;

/**
 * Description: 业务操作异常状态码
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public enum LogType {

    EXCEPTION("未知异常日志"),
    BUSSINESS("业务操作日志");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
