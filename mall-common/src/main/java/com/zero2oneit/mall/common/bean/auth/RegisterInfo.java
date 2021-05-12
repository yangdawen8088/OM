package com.zero2oneit.mall.common.bean.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author leon
 * @date 2020/9/4 15:52
 */
@Data
public class RegisterInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //账号，手机号码
    private String phone;
    //密码
    private String password;
    //邀请码
    private String invitation;
    //验证码
    private String code;
}
