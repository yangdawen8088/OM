package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@Data
@TableName("bargain_rule")
public class BargainRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @TableId
    private Long id;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //接收字符串时间处理
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //接收字符串时间处理
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endTime;
    /**
     * 砍价人数（需邀请多少人砍价成功）
     */
    private Integer bargainAmount;
    /**
     * 帮砍次数（单个商品用户可以帮砍的次数，例：次数设置为1，甲和乙同时将商品A的砍价链接发给丙，丙只能帮甲或乙其中一个人砍价）
     */
    private Integer helpCount;
    /**
     * 砍价次数（单个活动每个用户发起砍价次数限制）
     */
    private Integer bargainCount;
    /**
     * 规则状态：1-开启 2-未开启 3-关闭
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
