package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.Logging;
import com.zero2oneit.mall.common.query.member.LoggingObject;
import com.zero2oneit.mall.common.bean.member.MemberAccounts;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/7/23 17:42
 */
public interface LoggingService extends IService<Logging> {
    BoostrapDataGrid blackpearlLog(LoggingObject qo);

    BoostrapDataGrid integralLog(LoggingObject qo);

    BoostrapDataGrid voucherLog(LoggingObject qo);

    BoostrapDataGrid whitePearlLog(LoggingObject qo);

    /**
     * 黑珍珠记录
     * @param price 金额
     * @param balance 余额
     * @param type 类型
     * @param remark 备注
     * @param sign + / -
     */
    void insertBlackpearl(String userId, BigDecimal price, BigDecimal balance, Integer type, String remark, String sign);

    /**
     * 积分记录
     * @param price 金额
     * @param balance 余额
     * @param type 类型
     * @param remark 备注
     * @param sign + / -
     */
    void insertIntegral(String userId, BigDecimal price, BigDecimal balance, Integer type, String remark, String sign);


    /**
     * 券记录
     * @param price 金额
     * @param balance 余额
     * @param type 类型
     * @param remark 备注
     * @param sign + / -
     */
    void insertVoucher(String userId, BigDecimal price, BigDecimal balance, Integer type, String remark, String sign);



    /**
     * 白珍珠记录
     * @param price 金额
     * @param balance 余额
     * @param type 类型
     * @param remark 备注
     * @param sign + / -
     */
    void insertWhitePearl(String userId, BigDecimal price, BigDecimal balance, Integer type, String remark, String sign);


    void insertLog(MemberAccounts oldAccounts, MemberAccounts newAccounts, int type, String remark);



    R groupWhiteByMonth(LoggingObject loggingObject);

    R groupIntegralByMonth(LoggingObject loggingObject);

    R groupVoucherByMonth(LoggingObject loggingObject);

    BoostrapDataGrid commission(LoggingObject qo);

    void insertCommission(String memberId, BigDecimal commission, String totalCommission, String type, String remark, String sign);

    BoostrapDataGrid commissionSharing(LoggingObject qo);

    void insertShareMoney(String memberId, BigDecimal shareMoney, String totalCommission, String type, String remark, String sign);
}
