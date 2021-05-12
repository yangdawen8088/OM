package com.zero2oneit.mall.common.bean.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author leon
 * @date 2021/3/17 11:21
 */
@Data
public class MallOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;  //订单id
    private String memberId;  //会员Id
    private String outTradeNo;  //订单号
    private String storeId;  //商店Id
    private String storeName;
    private BigDecimal orderAmount;  //订单总金额
    private BigDecimal totalPrice;  //应付总金额
    private String payType; //支付渠道
    private Date createTime;   //订单创建时间
    private String note;    //订单中的备注
    private Integer orderState;   //订单状态【0->待付款；1->待发货；2->已发货/待收货；3->已完成；4->已关闭；5->取消订单;9-交易关闭】 全部商品申请退款后该订单交易关闭
    @TableField(exist = false)
    private List<MallGood> mallGoods;  //订单中的商品列表
    private Date cancelTime;  //订单取消时间
    private Date payTime;  //订单支付时间
    private Date deliveryTime;   //发货时间
    private Date confirmTime;   //确认收货时间
    private String logisticsCompany;  //物流公司
    private String logisticsNo;    //物流订单号
    private String address;    //地址_json形式
    private String goodItems;  //商品_json形式
    private Integer isDelete;
    private Integer goodNumber;//购买商品数量
    private String memberPhone;
    private String nickName;
    private Integer hasSaleAfter;
    private BigDecimal refundOrderAmount;  //退款商品总金额
}
