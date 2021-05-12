package com.zero2oneit.mall.common.bean.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @author leon
 * @date 2021/3/17 11:19
 */
@Data
public class MallOrderVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private OrderAddress orderAddress; //订单收货地址
    private List<MallOrder> mallOrders; //不同店家的订单
    private BigDecimal totalMoney; //应该支付的总金额
}
