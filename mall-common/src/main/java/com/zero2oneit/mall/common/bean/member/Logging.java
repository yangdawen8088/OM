package com.zero2oneit.mall.common.bean.member;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:黑珍珠记录表、积分记录表、优惠券记录表、白珍珠记录表,微信提现日志
 *
 * @author Atzel
 * @date 2020/7/23 17:48
 */
@Data
public class Logging implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * type_state ： 类型
     */
    private Integer typeState;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * + ，-  (消费或者充值)
     */
    private String sign;

    /**
     * 余额
     */
    private BigDecimal balance;

}
