package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Data
@TableName("goods_product_info_relation")
public class GoodsProductInfoRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品id,水平拆分
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Long id;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品当前库存
	 */
	private Integer productStock;
	/**
	 * 供货价
	 */
	private BigDecimal supplyPrice;
	/**
	 * 市场售价
	 */
	private BigDecimal marketPrice;
	/**
	 * 商品售价
	 */
	private BigDecimal productPrice;
	/**
	 * 销售区域名称
	 */
	private String saleAreaName;
	/**
	 * 已售数量
	 */
	private Integer soldStock;
	/**
	 * 虚拟销量
	 */
	private Integer saleAmount;

}
