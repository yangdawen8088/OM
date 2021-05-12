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
 * @date 2021-03-17
 */
@Data
@TableName("seckill_rule")
public class SeckillRule implements Serializable {

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
	 * 开始时间
	 */
		private Integer startTime;
		/**
	 * 结束时间
	 */
		private Integer endTime;
		/**
	 * 规则状态：1-开启 2-关闭
	 */
		private Integer ruleStatus;
		/**
	 * 规则排序
	 */
		private Integer ruleOrder;
		/**
	 * 操作人名称
	 */
		private String operatorName;
		/**
	 * 操作时间
	 */
		private Date operatorTime;
	
}
