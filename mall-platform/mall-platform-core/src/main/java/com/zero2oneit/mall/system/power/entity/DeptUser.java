package com.zero2oneit.mall.system.power.entity;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

@Data
public class DeptUser extends BaseTreeNode {

    //部门级别：0-顶级 1- 一级 2-二级
    private Integer deptLevel;

    //用户ID
    private Long userId;

}
