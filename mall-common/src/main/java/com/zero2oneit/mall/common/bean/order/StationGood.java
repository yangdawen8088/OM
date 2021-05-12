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
 * @date 2021/3/18 10:09
 */
@Data
public class StationGood implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;        //订单商品编号
    private Long skuId;
    private Integer count;//购买数量
    private BigDecimal price;//商品单件原价
    @TableField(exist = false)
    private List<String> skuAttr;
    private String goodSkuAttrs;//商品规格
    private String picture;   //商品名称
    private Integer productStock;
    private Long productId;
    private String productName;//商品名称
    @TableField(exist = false)
    private Boolean hasStock;   //是否有库存
    private String skuOne;
    private String skuOneName;
    private String skuTwo;
    private String skuTwoName;
    private Date payTime;  //订单取消时间
    private String stationName;   //驿站名称
    private Long stationId;   //驿站名称
    private String memberId;
    private String orderId;
    private Integer deleted;     //是否删除,0-未删除，1-已删除
    private String memberPhone;
    private String nickName;
    @TableField(exist = false)
    private Integer applyCount; //售后申请数量
    @TableField(exist = false)
    private Integer orderItemState;  //商品状态 -1->正常状态； 0->退款中;1->退款成功;2->已拒绝；3-撤销申请
    private BigDecimal realPrice;   //商品单件实付价（减去优惠后的单价）
    private Integer refundCount; //成功退款数量
}
