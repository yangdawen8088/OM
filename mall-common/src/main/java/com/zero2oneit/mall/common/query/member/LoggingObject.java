package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:黑珍珠记录表、积分记录表、优惠券记录表、白珍珠记录表
 *
 * @author Atzel
 * @date 2020/7/23 18:04
 */
@Data
public class LoggingObject extends QueryObject {

    /**
     * 会员id (会员账号)
     */
    private String memberId;

    private String type;

    //根据（期间）查询创建时间的数据
    private String startTime;//开始时间
    private String endTime;//结束时间

    //消费或充值
    private String sign;


    private  String monthTime;//前端





}
