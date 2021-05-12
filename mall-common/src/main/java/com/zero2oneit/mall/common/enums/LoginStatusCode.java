package com.zero2oneit.mall.common.enums;

/**
 * Description: 平台登录响应状态码
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public enum LoginStatusCode {

    STATUSCODE_NULL(10000,"系统运行状态不能空"),
    ACCOUNT_NULL(10001,"用户账号不能空"),
    PASSWORD_NULL(10002,"用户密码不能空"),
    VALIDCODE_NULL(10003,"验证码不能空"),
    LOGIN_VALIDCODE(10004,"输入验证码不正确"),
    USER_ISNULL(10005,"输入用户账号或密码不正确"),
    OLDPASSWORD_NULL(10006,"原密码不能空"),
    NEWPASSWORD_NULL(10007,"新密码不能空"),
    IS_SAME(10008,"输入原密码不能和新密码相同"),
    EDIT_PASSWORD(10009,"输入原密码不正确"),
    USERNAME_NULL(10010,"输入用户名称不能为空"),
    USER_EXISTS(10011,"该账号已存在"),
    USER_UNUSED(10012,"该账号已被禁用，请联系管理员"),
    FAILURE(500,"程序异常");

    private final Integer code;

    private final String message;

    LoginStatusCode(Integer code, String message) {
        this.code = code;
        this.message = message ;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage(){
        StringBuilder sb = new StringBuilder() ;
        sb.append(this.getMessage()).append("[").append(this.getCode()).append("]");
        return sb.toString();
    }

}
