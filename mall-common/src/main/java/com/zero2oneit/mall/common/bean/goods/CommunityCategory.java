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
 * @date 2021-02-05
 */
@Data
@TableName("community_category")
public class CommunityCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @TableId
    private Long id;
    /**
     * 商品分类名称
     */
    private String categoryName;
    /**
     * 商品分类排序
     */
    private Integer categoryOrder;
    /**
     * 图标
     */
    private String logo;

}
