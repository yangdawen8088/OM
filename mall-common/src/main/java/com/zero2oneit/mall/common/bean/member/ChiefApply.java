package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-27
 */
@Data
@TableName("chief_apply")
public class ChiefApply implements Serializable {

	private static final long serialVersionUID = 1L;

    	/**
	 * id自增
	 */
		@TableId
		private Long id;
		/**
	 * 会员id
	 */
		private String memberId;
		/**
	 * 提货点名称
	 */
		private String pickName;
		/**
	 * 提货点地址
	 */
		private String pickAddr;
		/**
	 * 门牌号
	 */
		private String houseNum;
		/**
	 * 纬度
	 */
		private String latitude;
		/**
	 * 经度
	 */
		private String longitude;
		/**
	 * 开始营业时间
	 */
		private String openHours;
		/**
	 * 结束营业时间
	 */
		private String endHours;
		/**
	 * 身份证正面
	 */
		private String cardFront;
		/**
	 * 身份证反面
	 */
		private String cardBack;
		/**
	 * 申请人电话
	 */
		private String phone;
		/**
	 * 门牌照
	 */
		private String doorPhoto;
		/**
	 * 申请时间
	 */
		private Date createTime;

	  /**
	   * 状态：1 -开启 0 -关闭
	   */
	  private Integer switchId;

		/**
	 * 状态：0-审核中 1-审核通过 2-审核不通过
	 */
		private Integer statusId;
		/**
	 * 处理人名称
	 */
		private String operatorName;
		/**
	 * 处理时间
	 */
		private Date operatorTime;

}
