package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员粉丝管理表
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
@Data
@TableName("member_fans")
public class MemberFans implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前会员id
	 */
		private String memberId;
		/**
	 * 级别：1：一级 2：二级
	 */
		private Integer levelType;
		/**
	 * 粉丝ID
	 */
		private String fansId;
		/**
	 * 粉丝名称
	 */
		private String fansName;
		/**
	 * 粉丝等级：0-非会员 1-会员
	 */
		private Integer fansGrade;
		/**
	 * 邀请时间
	 */
		private Date inviteTime;

}
