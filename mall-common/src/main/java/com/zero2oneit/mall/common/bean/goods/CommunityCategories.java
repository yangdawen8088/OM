package com.zero2oneit.mall.common.bean.goods;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Data
public class CommunityCategories extends BaseTreeNode {

    /**
     * 分类级别:0-顶级 1- 一级 2-二级 3-三级
     */
    private Integer categoryLevel;
    /**
     * 分类排序
     */
    private Integer orderId;
    /**
     * 分类状态：0-注销 1-有效
     */
    private Integer categoryStatus;
    /**
     * 配置图标
     */
    private String iconCls;

}
