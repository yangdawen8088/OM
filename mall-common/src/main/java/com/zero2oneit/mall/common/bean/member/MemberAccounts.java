package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员账户管理表
 *
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
@Data
@TableName("member_accounts")
public class MemberAccounts implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 分布式ID
	 */
	@TableId(value = "member_id", type = IdType.INPUT)
	private String memberId;
	/**
	 * 我的金券
	 */
	private Integer goldCoupon;
	/**
	 * 我的积分-购物积分
	 */
	private BigDecimal memberPoints;
	/**
	 * 我的白珍珠
	 */
	private BigDecimal whitePearl;

	//佣金
	private BigDecimal commission;

	//分享佣金
	private BigDecimal shareMoney;

}
