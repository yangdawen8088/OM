package com.zero2oneit.mall.common.enums;

/**
 * Description:日志响应状态码
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public enum LogSucceed {

    SUCCESS("成功"),
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
