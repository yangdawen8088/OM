package com.zero2oneit.mall.common.bean.goods;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-06
 */
@Data
public class GoodSaleAreaTree extends BaseTreeNode {

    /**
     * 分类级别:0-顶级 1- 一级 2-二级 3-三级
     */
    private Integer level;

    /**
     * 分类状态：0-注销 1-有效
     */
    private Integer status;

    /**
     * 纬度
     */
    private double lat;

    /**
     * 经度
     */
    private double lon;

    /**
     * 地址
     */
    private String address;
}
