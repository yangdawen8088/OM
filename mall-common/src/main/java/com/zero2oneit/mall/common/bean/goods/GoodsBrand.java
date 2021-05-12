package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-19
 */
@Data
@TableName("goods_brand")
public class GoodsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
     */
    @TableId
    private Long id;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌首字母
     */
    private String initial;
    /**
     * 分类排序
     */
    private Integer orderId;
    /**
     * 分类状态：0-注销 1-有效
     */
    private Integer brandStatus;
    /**
     * 品牌LOGO
     */
    private String logo;
    /**
     * 商品分类ID
     */
    private Long categoryId;
    /**
     * 商品分类名称
     */
    private String categoryName;

}
