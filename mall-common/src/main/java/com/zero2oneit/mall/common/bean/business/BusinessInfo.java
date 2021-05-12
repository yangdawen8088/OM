package com.zero2oneit.mall.common.bean.business;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 商家信息（社区）
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-23
 */
@Data
@TableName("business_info")
public class BusinessInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商家id
	 */
	@TableId
	private Long businessId;
	/**
	 * 商家昵称
	 */
	private String businessName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 商家账号:使用手机号作为账号
	 */
	private String account;
	/**
	 * 商家类型：1-个人 2-企业
	 */
	private Integer businessType;
	/**
	 * 食品流通许可证
	 */
	private String foodcirImage;
	/**
	 * 省
	 */
	private Integer provinceId;
	/**
	 * 市
	 */
	private Integer cityId;
	/**
	 * 区
	 */
	private Integer areaId;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 联系地址
	 */
	private String storeAddress;
	/**
	 * 联系人姓名
	 */
	private String contactRen;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 手执身份证照片 正面
	 */
	private String idCardP;
	/**
	 * 手执身份证照片 背面
	 */
	private String idCardA;
	/**
	 * 营业执照电子版文件
	 */
	private String businessLicense;
	/**
	 * 省、市、区
	 */
	private String bankAddress;
	/**
	 * 企业结算银行开户名
	 */
	private String settleBankName;
	/**
	 * 企业结算银行账号
	 */
	private String settleBankAccount;
	/**
	 * 企业结算开户银行支行名称
	 */
	private String settleBankSubbranch;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 推荐人手机号
	 */
	private String referPhone;
	/**
	 * 审核人id
	 */
	private String approveId;
	/**
	 * 审核状态：1-审核中 2-审核通过 3-审核不通过
	 */
	private Integer approveStatus;
	/**
	 * 审核结果
	 */
	private String approveResult;
	/**
	 * 审核时间
	 */
	private Date approveTime;
	
}
