package com.zero2oneit.mall.system.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //用户id自增
    private Long id;

    //用户账户
    private String account;

    //用户名称
    private String username;

    //用户电话
    private String phone;

    //用户邮箱
    private String email;

    //用户头像图片路径
    private String headPhoto;

    //部门id
    private long deptId;

    //用户状态：0-注销、1-有效
    private int userStatus = 1;

    //简介
    private String profile;

    //驿站id
    private Integer stationId;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

}
