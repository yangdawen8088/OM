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
 * @date 2020/12/29 15:12
 */
@Data
@TableName(value = "distribute_log")
public class DistributeLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String Id;  //订单id
    private BigDecimal price;
    private Integer typeState;
    private String remark;
    private Date createTime;
    private String sign;
    private BigDecimal balance;
    private String businessId;
}
