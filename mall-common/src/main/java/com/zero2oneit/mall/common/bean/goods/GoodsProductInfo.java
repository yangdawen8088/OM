package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 商品信息表（社区）
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Data
@TableName("goods_product_info")
public class GoodsProductInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品id,水平拆分
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Long id;
	/**
	 * 商家id、水平拆分
	 */
	private Long businessId;
	/**
	 * 商家名称
	 */
	private String businessName;
	/**
	 * 商品类型id
	 */
	private Long typeId;
	/**
	 * 商品类型名称
	 */
	private String typeName;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品条形码
	 */
	private String barCode;
	/**
	 * 供货价
	 */
	@TableField(exist = false)
	private String supplyPrice;
	/**
	 * 市场售价
	 */
	@TableField(exist = false)
	private String marketPrice;
	/**
	 * 商品售价
	 */
	@TableField(exist = false)
	private String productPrice;
	/**
	 * 商品预警值
	 */
	private Integer warningValue;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 商品规格
	 */
	private String skuName;
	/**
	 * 商品单位
	 */
	private String productUnit;
	/**
	 * 商品重量
	 */
	private String productWeight;
	/**
	 * 商品重量单位
	 */
	private String productWeightUnit;
	/**
	 * 商品生产地
	 */
	private String productPlace;
	/**
	 * 商品主图路径
	 */
	private String mainPicture;
	/**
	 * 商品详情图路径
	 */
	private String detailPicture;
	/**
	 * 白底图片（浏览图片）路径
	 */
	private String whitePicture;
	/**
	 * 实物图片路径
	 */
	private String physicalPicture;

	/**
	 * 申请时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date applyTime;
	/**
	 * 审核时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date approveTime;
	/**
	 * 审核人姓名
	 */
	private String approveName;
	/**
	 * 商品状态：0-审核中 1-审核通过||上架 2-审核不通过 3-违规 4-下架 5-删除
	 */
	private Integer productStatus;
	/**
	 * 审核结果
	 */
	private String approveResult;
	/**
	 * 短视频地址
	 */
	private String videoSrc;

	/**
	 * 销售区域名称
	 */
	@TableField(exist = false)
	private String saleAreaName;
	/**
	 * 排序
	 */
	private Integer productSort;
	/**
	 * 限购数量
	 */
	private Integer buyLimit;
	/**
	 * 生产日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date produceTime;
	/**
	 * 保质期
	 */
	private String lifeTime;
	/**
	 * 保质期单位
	 */
	private String lifeTimeUnit;
	/**
	 * 存储条件
	 */
	private String storeCondition;
	/**
	 * 商品当前库存
	 * 表示该属性不为数据库表字段，但又是必须使用的。
	 */
	@TableField(exist = false)
	private String productStock;
	/**
	 * 虚拟销量
	 */
	@TableField(exist = false)
	private String saleAmount;

}
