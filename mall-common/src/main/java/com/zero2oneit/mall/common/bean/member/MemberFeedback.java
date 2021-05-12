package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@Data
@TableName("member_feedback")
public class MemberFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @TableId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 意见反馈内容
     */
    private String feedBack;
    /**
     * 图片路径
     */
    private String photoUrl;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态：0-未处理 1-已处理
     */
    private Integer statusId;
    /**
     * 处理人名称
     */
    private String operatorName;
    /**
     * 处理时间
     */
    private Date operatorTime;
    /**
     * 处理备注
     */
    private String note;

}
