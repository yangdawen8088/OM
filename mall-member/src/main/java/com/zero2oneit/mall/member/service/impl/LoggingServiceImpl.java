package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.Logging;
import com.zero2oneit.mall.common.query.member.LoggingObject;
import com.zero2oneit.mall.common.bean.member.MemberAccounts;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.LoggingMapper;
import com.zero2oneit.mall.member.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/7/23 17:42
 */
@Service
public class LoggingServiceImpl extends ServiceImpl<com.zero2oneit.mall.member.mapper.LoggingMapper, Logging> implements LoggingService {

    @Autowired
    private LoggingMapper LoggingMapper;

    @Override
    public BoostrapDataGrid blackpearlLog(LoggingObject qo) {

       int total = LoggingMapper.blackpearlLogTotal(qo);

       return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.blackpearlLogRows(qo));
    }

    @Override
    public BoostrapDataGrid integralLog(LoggingObject qo) {
        int total = LoggingMapper.integralLogTotal(qo);

        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.integralLogRows(qo));
    }

    @Override
    public BoostrapDataGrid voucherLog(LoggingObject qo) {
        int total = LoggingMapper.voucherLogTotal(qo);

        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.voucherLogRows(qo));
    }

    @Override
    public BoostrapDataGrid whitePearlLog(LoggingObject qo) {
        int total = LoggingMapper.whitePearlLogTotal(qo);

        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.whitePearlLogRows(qo));
    }

    @Override
    public void insertBlackpearl(String userId,BigDecimal price, BigDecimal balance, Integer type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",userId);
        param.put("price",price);
        param.put("balance",balance);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        LoggingMapper.insertBlackpearl(param);
    }

    @Override
    public void insertIntegral(String userId,BigDecimal price, BigDecimal balance, Integer type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",userId);
        param.put("price",price);
        param.put("balance",balance);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        LoggingMapper.insertIntegral(param);
    }

    @Override
    public void insertVoucher(String userId,BigDecimal price, BigDecimal balance, Integer type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",userId);
        param.put("price",price);
        param.put("balance",balance);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        LoggingMapper.insertVoucher(param);
    }

    @Override
    public void insertWhitePearl(String userId,BigDecimal price, BigDecimal balance, Integer type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",userId);
        param.put("price",price);
        param.put("balance",balance);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        LoggingMapper.insertWhitePearl(param);
    }




    @Override
    public void insertLog(MemberAccounts oldAccounts, MemberAccounts newAccounts, int type, String remark) {
        if(oldAccounts.getMemberPoints() != null && !"".equals(oldAccounts.getMemberPoints()) && ! "0".equals(oldAccounts.getMemberPoints().toString())){
            BigDecimal price=oldAccounts.getMemberPoints();
            String sign= price.compareTo(BigDecimal.ZERO)> 0 ? "+" :"-";
            insertIntegral(newAccounts.getMemberId(),price,newAccounts.getMemberPoints(),type,remark,sign);
        }
        if(oldAccounts.getGoldCoupon()!=null && oldAccounts.getGoldCoupon()!= 0 ){
            BigDecimal price=new BigDecimal(oldAccounts.getGoldCoupon());
            String sign=price.compareTo(BigDecimal.ZERO)> 0  ?  "+" :"-";
            insertVoucher(newAccounts.getMemberId(),price,new BigDecimal(newAccounts.getGoldCoupon()),type,remark,sign);
        }
        if(oldAccounts.getWhitePearl() != null &&!"".equals(oldAccounts.getWhitePearl()) && ! "0".equals(oldAccounts.getWhitePearl().toString())){
            BigDecimal price=oldAccounts.getWhitePearl();
            String sign=price.compareTo(BigDecimal.ZERO) >0 ? "+" :"-";
            insertWhitePearl(newAccounts.getMemberId(),price,newAccounts.getWhitePearl(),type,remark,sign);
        }

    }

    @Override
    public R groupWhiteByMonth(LoggingObject loggingObject) {
        List<HashMap<String, Object>> hashMaps = LoggingMapper.groupWhiteByMonth(loggingObject);
        return R.ok(hashMaps);
    }

    @Override
    public R groupIntegralByMonth(LoggingObject loggingObject) {
        List<HashMap<String, Object>> hashMaps = LoggingMapper.groupIntegralByMonth(loggingObject);
        return R.ok(hashMaps);
    }

    @Override
    public R groupVoucherByMonth(LoggingObject loggingObject) {
        List<HashMap<String, Object>> hashMaps = LoggingMapper.groupVoucherByMonth(loggingObject);
        return R.ok(hashMaps);
    }

    @Override
    public BoostrapDataGrid commission(LoggingObject qo) {
        int total = LoggingMapper.commissionTotal(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.commissionRows(qo));
    }

    @Override
    public void insertCommission(String memberId, BigDecimal commission, String totalCommission, String type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",memberId);
        param.put("price",commission);
        param.put("balance",totalCommission);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        System.out.println(param);
        LoggingMapper.insertCommission(param);
    }

    @Override
    public BoostrapDataGrid commissionSharing(LoggingObject qo) {
        int total = LoggingMapper.sharingTotal(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : LoggingMapper.sharingRows(qo));
    }

    @Override
    public void insertShareMoney(String memberId, BigDecimal shareMoney, String totalCommission, String type, String remark, String sign) {
        Map param=new HashMap<>();
        param.put("userId",memberId);
        param.put("price",shareMoney);
        param.put("balance",totalCommission);
        param.put("type",type);
        param.put("remark",remark);
        param.put("sign",sign);
        LoggingMapper.insertShareMoney(param);
    }


}
