package com.zero2oneit.mall.system.dept.entity;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

@Data
public class Dept extends BaseTreeNode {

    //部门级别：0-顶级 1- 一级 2-二级
    private int deptLevel;

    //部门负责人ID
    private long masterId;

}
