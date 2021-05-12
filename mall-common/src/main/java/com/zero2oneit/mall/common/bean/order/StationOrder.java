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
 * @date 2021/3/18 10:08
 */
@Data
public class StationOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;        //订单Id
    private String memberId;  //会员Id
    private String outTradeNo;  //订单号
    private Long stationId;    //驿站Id
    private String stationName;   //驿站名称
    private BigDecimal orderAmount;  //订单总金额
    private Date createTime;   //订单创建时间
    private String note;    //订单中的备注
    private Integer orderState;
    //订单状态 0->待付款；1->待收货；2->待取货；3->已完成；4->已关闭；5->取消订单(未支付)
    // 6:审核中,7:审核成功,8：审核失败,9-付款成功,10-业务处理失败,11-撤销退款申请,12-交易关闭
    //9-付款成功 是微信或支付宝 支付callback的时候临时标识
    private Date payTime;  //订单支付时间
    private Date deliveryTime;   //发货时间
    private Date confirmTime;   //确认收货时间
    private String payType; //支付渠道
    @TableField(exist = false)
    private List<StationGood> stationGoods;  //订单中的商品列表
    @TableField(exist = false)
    private OrderAddress orderAddress; //订单收货地址
    private String address;  //地址_json形式
    private String goodItems;  //_json形式
    private Integer receiveType; //取货方式 0-自提，1-配送
    private Integer isDelete;     //是否删除,0-未删除，1-已删除
    private Integer goodNumber;//购买商品数量
    private String describes;     //退款说明describe
    private Date applyTime;   //申请时间
    private Date approveTime;   //审核时间
    private BigDecimal realOrderAmount;  //订单实付总金额
    private String memberPhone;
    private String nickName;
    private Integer hasSaleAfter;  //是否有售后申请 0-无,1-有
    private BigDecimal refundOrderAmount;  //退款商品总金额
}
