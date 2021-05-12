package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.query.member.DistributionObject;
import com.zero2oneit.mall.common.bean.member.MechanismDistribute;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/9/2 15:21
 */
@Mapper
public interface DistributionMapper extends BaseMapper<MechanismDistribute> {

    int total(DistributionObject qo);

    List<HashMap<String,Object>> DistributionListRows(DistributionObject qo);

    BigDecimal getProportion();

    void marketKgEdit(Map map);

    void postKgEdit(Map map);

    void migrantKgEdit(Map map);

    MechanismDistribute commissionInquire();

    MechanismDistribute commissionSwitch();

    MechanismDistribute memberSwitch();

    MechanismDistribute withdrawalSwitch();

    MechanismDistribute sharingCommissionRules();

}
