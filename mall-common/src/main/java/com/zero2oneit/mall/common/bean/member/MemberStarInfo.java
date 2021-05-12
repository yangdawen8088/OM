package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author Sinper
 * @create 2021-02-19
 * @description
 */
@Data
@TableName("member_star_info")
public class MemberStarInfo implements Serializable {

	private static final long serialVersionUID = 1L;

    	/**
	 *
	 */
		private Long memberId;
		/**
	 * 累计消费
	 */
		private BigDecimal addConsume;
		/**
	 * 星级id
	 */
		private Integer starId;
		/**
	 * 累计直属一星达人
	 */
		private Integer addoneStar;
		/**
	 * 累计直属二星达人
	 */
		private Integer addtwoStar;
		/**
	 * 累计直属三星达人
	 */
		private Integer addthreeStar;
		/**
	 * 创建时间
	 */
		private Date creationTime;
		/**
	 * 本月直属一星达人
	 */
		private Integer oneStar;
		/**
	 * 本月直属二星达人
	 */
		private Integer twoStar;
		/**
	 * 本月直属三星达人
	 */
		private Integer threeStar;
		/**
	 * 本月累计消费
	 */
		private BigDecimal consumes;
		/**
	 * 星级是否开启1是开启，0是关闭
	 */
		private Integer starOpen;
		/**
	 * 旗下一星达人
	 */
		private Integer subOneStar;
		/**
	 * 旗下二星达人
	 */
		private Integer subTwoStar;
		/**
	 * 会员名称
	 */
		private String memberAccount;
		/**
	 * 旗下三星达人
	 */
		private Integer subThreeStar;

}
