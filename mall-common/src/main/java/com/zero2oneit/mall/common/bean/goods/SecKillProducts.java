package com.zero2oneit.mall.common.bean.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Description: 秒杀商品信息表（社区）
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */

/**
 * indexName : 索引名称
 * shards ：分片
 * replicas ：副本
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "secKillProducts", shards = 1, replicas = 0)
public class SecKillProducts {

	/**
	 * 商品id,水平拆分
	 */
	@Id
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
	@Field(type = FieldType.Keyword)
	private Long typeId;
	/**
	 * 商品类型名称
	 */
	private String typeName;
	/**
	 * 商品名称
	 */
	@Field(type = FieldType.Text)
	private String productName;
	/**
	 * 商品条形码
	 */
	private String barCode;
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
	 * 商品状态：0-审核中 1-审核通过||上架 2-审核不通过 3-违规 4-下架 5-删除
	 */
	private Integer productStatus;
	/**
	 * 短视频地址
	 */
	private String videoSrc;
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
	 * 存储条件
	 */
	private Integer moudleId;

	ProductsRelation relation;

	/**
	 * 秒杀开始时间
	 */
	@Field(type = FieldType.Keyword)
	private Integer startTime;

}
