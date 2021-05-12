package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.bean.member.Logging;
import com.zero2oneit.mall.common.query.member.LoggingObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/7/23 17:41
 */
@Mapper
public interface LoggingMapper extends BaseMapper<Logging> {

    int blackpearlLogTotal(LoggingObject qo);
    List<HashMap<String,Object>> blackpearlLogRows(LoggingObject qo);

    int integralLogTotal(LoggingObject qo);
    List<HashMap<String,Object>> integralLogRows(LoggingObject qo);

    int voucherLogTotal(LoggingObject qo);
    List<HashMap<String,Object>> voucherLogRows(LoggingObject qo);

    int whitePearlLogTotal(LoggingObject qo);
    List<HashMap<String,Object>> whitePearlLogRows(LoggingObject qo);

    void insertBlackpearl(Map param);

    void insertIntegral(Map param);

    void insertVoucher(Map param);

    void insertWhitePearl(Map param);

    List<HashMap<String,Object>> groupWhiteByMonth(LoggingObject loggingObject);

    List<HashMap<String,Object>> groupIntegralByMonth(LoggingObject loggingObject);

    List<HashMap<String,Object>> groupVoucherByMonth(LoggingObject loggingObject);

    int commissionTotal(LoggingObject qo);
    List<HashMap<String,Object>> commissionRows(LoggingObject qo);

    void insertCommission(Map param);

    int sharingTotal(LoggingObject qo);

    List<HashMap<String,Object>> sharingRows(LoggingObject qo);

    void insertShareMoney(Map param);

}
