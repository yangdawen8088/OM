package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods_area_house")
public class GoodSaleArea {

    /**
     * id
     */
    @TableId(type=IdType.INPUT)
    private Long id;

    /**
     * id
     */
    private String name;
    /**
     * 父级id
     */
    private Integer parentId;

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
    private Double lat;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 地址
     */
    private String address;
}
