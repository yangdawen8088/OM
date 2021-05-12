package com.zero2oneit.mall.common.bean.goods;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Data
public class ProductsRelation {

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
