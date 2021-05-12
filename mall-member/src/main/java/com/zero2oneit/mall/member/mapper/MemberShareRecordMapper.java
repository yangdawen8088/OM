package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.query.member.MemberShareObject;
import com.zero2oneit.mall.common.query.member.MemberShareQueryObject;
import com.zero2oneit.mall.common.bean.member.MemberShareRecord;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author leon
 * @date 2021/1/19 17:05
 */
@Mapper
public interface MemberShareRecordMapper extends BaseMapper<MemberShareRecord> {

    int memberShareRecordCount(MemberShareQueryObject memberShareQueryObject);

    List<HashMap<String,Object>> queryShareOrderList(MemberShareQueryObject memberShareQueryObject);

    Map<String, BigDecimal> selectShareMoney(String orderId);

    int listTotal(MemberShareObject qo);

    List<HashMap<String,Object>> listRow(MemberShareObject qo);

}
