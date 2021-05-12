package com.zero2oneit.mall.system.role.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    //角色ID
    private  long id;

    //角色名称
    private  String roleName;

    //角色编码
    private  String roleCode;

    //创建人ID
    private  long userId;

    //创建时间
    private Date createTime;

    //是否超级管理员:0-不是 1-是
    private  int superAdmin = 0;

    //备注
    private  String note;

    public Role(String roleName, String roleCode, long userId, Date createTime, String note) {
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.userId = userId;
        this.createTime = createTime;
        this.note = note;
    }

}
