package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 *
 * @author leon
 * @date 2021/1/19 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "member_share_record")
public class MemberShareRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;        //订单Id
    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 购买数
     */
    private Integer count;

    /**
     * 分享佣金
     */
    private BigDecimal share;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 购买人昵称
     */
    private String nickName;

    /**
     * 购买人手机号
     */
    private String memberPhone;

    /**
     * 1-是一级，2-是二级
     */
    private Integer level;

    /**
     * 0-待获得，1-已获得
     */
    private Integer status;

    /**
     * 平台名称
     */
    private String platform;

    /**
     * 订单商品表id
     */
    private String goodId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 退款数量
     */
    private Integer refundCount;

}
