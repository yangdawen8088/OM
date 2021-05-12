package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author Sinper
 * @create 2020-12-18
 * @description
 */
@Data
@TableName("sys_membership_distribution")
public class MembershipDistribution implements Serializable {

	private static final long serialVersionUID = 1L;

    	/**
	 * ID
	 */
		@TableId
		private Long id;
		/**
	 * 名称
	 */
		private String name;
		/**
	 * 开关：0-关，1-开
	 */
		private String open;
		/**
	 * 一级
	 */
		private String oneLevel;
		/**
	 * 二级
	 */
		private String twoLevel;
		/**
	 * 类型：1-商城,2-驿站，3-候鸟
	 */
		private Integer type;

}
