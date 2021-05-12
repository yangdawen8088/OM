package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-17
 */
@Data
@TableName("integral_rule")
public class IntegralRule implements Serializable {

	private static final long serialVersionUID = 1L;

    	/**
	 * id自增
	 */
		@TableId
		private Long id;
		/**
	 * 规则名称
	 */
		private String ruleName;
		/**
	 * 积分类型：1-积分抵用比例(1积分抵多少金额) 2-下单赠送积分比例（实际支付1元赠送多少积分）
	 */
		private Integer integralType;
		/**
	 * 转换比例
	 */
		private BigDecimal convertRatio;
		/**
	 * 操作人名称
	 */
		private String operatorName;
		/**
	 * 操作时间
	 */
		private Date operatorTime;
	
}
