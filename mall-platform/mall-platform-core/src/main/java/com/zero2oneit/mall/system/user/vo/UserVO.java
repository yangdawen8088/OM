package com.zero2oneit.mall.system.user.vo;

import lombok.Data;

@Data
public class UserVO {

    private Long id;//
    private String account;//账号
    private String password;//原密码
    private String authCode;//验证码

    //用户名称
    private String username;
    //用户邮箱
    private String email;
    //简介
    private String profile;

    private String newPwd;//新密码
    //部门ID
    private Integer deptId;
    //当前操作用户id
    private Long userId;
    //角色编码
    private String[] roleCodes;

    public UserVO(String account, String password, String authCode) {
        this.account = account;
        this.password = password;
        this.authCode = authCode;
    }

}
