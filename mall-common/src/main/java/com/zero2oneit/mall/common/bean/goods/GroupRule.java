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
@TableName("group_rule")
public class GroupRule implements Serializable {

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
     * 拼团时效(单位 小时)：用户发起拼团后开始计时，需在设置时间内邀请到规定好友人数参团，超过时效时间，则系统判定拼团失败，自动发起退款
     */
    private Integer groupPeriod;
    /**
     * 拼团人数（单次拼团需要参与的用户数）
     */
    private Integer groupCount;
    /**
     * 购买数量限制（活动时间内每个用户参与拼团的次数限制。例如设置为4，表示本次活动有效期内，每个用户最多可参与购买4次）
     */
    private Integer buyLimit;
    /**
     * 单次购买数量限制（用户参与拼团时，一次购买最大数量限制。例如设置为2，表示参与拼团时，用户一次购买数量最大可选择2个）
     */
    private Integer singleBuyLimit;
    /**
     * 补齐人数（当用户参与拼团后，成团时效内未成团情况下，设置补齐人数可虚拟成团。例如：成团人数为10人，补齐人数为4人，真实用户需要参与6人成团才可以在最后未成团时自动）
     */
    private Integer supplyCount;
    /**
     * 规则状态：1-开启 2-关闭
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
