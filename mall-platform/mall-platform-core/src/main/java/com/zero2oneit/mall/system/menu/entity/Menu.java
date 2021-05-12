package com.zero2oneit.mall.system.menu.entity;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

@Data
public class Menu extends BaseTreeNode {

    /**
     * 菜单级别 ： 0-顶级 1- 一级 2-二级 3-三级
     */
    private Integer menuLevel;

    /**
     * 排序
     */
    private Integer orderId;

    /**
     * 功能id
     */
    private Long functionId;

    /**
     * 配置菜单图标
     */
    private String iconCls;

    /**
     * 菜单路径
     */
    private String menuUrl;

    /**
     * 请求接口
     */
    private String requestUrl;

    /**
     * 是否需要授权：0-不需要 1-需要
     */
    private Integer accessRule;

    /**
     * 功能类型：1-菜单、2-按钮
     */
    private Integer ftype;

}
