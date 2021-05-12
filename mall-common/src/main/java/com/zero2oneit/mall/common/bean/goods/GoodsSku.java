package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-08
 */
@Data
@TableName("goods_sku")
public class GoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * SKU-id,水平拆分
     */
	@TableId(value = "id",type = IdType.INPUT)
    private Long id;
    /**
     * 商品id,水平拆分
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 商品当前库存
     */
    private Integer productStock;
    /**
     * 已售数量
     */
    private Integer soldStock;
    /**
     * 供货价
     */
    private BigDecimal supplyPrice;
    /**
     * 市场售价
     */
    private BigDecimal marketPrice;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品缩略图
     */
    private String productPicture;
    /**
     * 商品重量：单位-KG
     */
    private String productWeight;
    /**
     * 商品体积：单位-m³
     */
    private String productVolume;

}
