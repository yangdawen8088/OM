package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@Data
@TableName("member_address")
public class MemberAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private String id;
    /**
     * 分布式ID
     */
    private String memberId;
    /**
     * 会员昵称
     */
    private String nickName;
    /**
     * 收货人名称
     */
    private String receiverName;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 街道地址
     */
    private String streetAddr;
    /**
     * 收货人手机号码
     */
    private String receivePhone;
    /**
     * 是否默认地址：0-不是 1-是  默认地址只能一个
     */
    private Integer isDefault;

}
