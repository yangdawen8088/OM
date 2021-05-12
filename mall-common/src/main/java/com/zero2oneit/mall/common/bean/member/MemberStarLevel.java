package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author zhj
 * @create 2021-01-27
 * @description
 */
@Data
@TableName("member_star_level")
public class MemberStarLevel implements Serializable {

	private static final long serialVersionUID = 1L;

    	/**
	 *
	 */
		@TableId
		private Long id;
		/**
	 * 星级名称
	 */
		private String starName;
		/**
	 * 成为星级条件一
	 */
		private String oneCondition;
		/**
	 * 成为星级条件二
	 */
		private String twoCondition;
		/**
	 * 团队培养奖1一级佣金收入比例
	 */
		private BigDecimal oneManage;
		/**
	 * 团队培养奖2二级佣金收入比例
	 */
		private BigDecimal twoManage;
		/**
	 * 全网分红比例
	 */
		private BigDecimal allDividends;
		/**
	 * 星级过期时间
	 */
		private Integer overdueNum;
		/**
	 * 账户
	 */
		private BigDecimal wallet;

}
