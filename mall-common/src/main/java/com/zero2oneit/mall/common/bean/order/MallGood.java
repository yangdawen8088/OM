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
 * @date 2021/3/17 11:24
 */
@Data
public class MallGood implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;  //订单id
    private Long skuId;     //产品Id
    private Integer count;      //商品购买数
    private BigDecimal price;    //商品单价
    private String title;       //标题
    private BigDecimal totalPrice;   //商品总价
    @TableField(exist = false)
    private Integer orderItemState;  //商品状态 0-已付款,1->退款中(无需退货)；2->退货退款中；3->换货中；4->退款成功；5->退货退款成功，6->换货成功，7->已撤销申请；8->已拒绝
    @TableField(exist = false)
    private List<String> skuAttr;
    private String skuAttrs;
    private String picture;      //商品图片
    private String storeId;
    private String storeName;
    private Integer productStock;
    private Long productId;
    private Date payTime;
    private BigDecimal favourCurrentcy;
    @TableField(exist = false)
    private Boolean hasStock;  //是否有库存
    @TableField(exist = false)
    private BigDecimal orderFreight;  //邮费
    @TableField(exist = false)
    private Boolean checked=true;
    @TableField(exist = false)
    private String outTradeNo;  //订单号
    @TableField(exist = false)
    private Integer applyCount; //售后申请数量
    private String orderId;
    private Integer refundCount; //成功退款数量
}
