package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@Data
@TableName("coupon_rule")
public class CouponRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @TableId
    private Long id;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券类型：1-满减券 2-商品券 3-赠送券 4-新人券 5-通用券
     */
    private Integer couponType;
    /**
     * 优惠券面值
     */
    private Integer faceValue;
    /**
     * 面值条件（为空表示通用型，不为空表示满减型）
     */
    private Integer faceValueDemand;
    /**
     * 优惠券有效期开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //接收字符串时间处理
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 优惠券有效期结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //接收字符串时间处理
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 领取类型：1-手动领取 2-自动领取
     */
    private Integer receiveType;
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
