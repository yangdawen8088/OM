package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.query.member.HyDistributionObject;
import com.zero2oneit.mall.common.bean.member.MembershipDistribution;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sinper
 * @create 2020-12-18
 * @description
 */
@Mapper
public interface MembershipDistributionMapper extends BaseMapper<MembershipDistribution> {

    List<HashMap<String,Object>> distributionRecord(HyDistributionObject qo);

    int distributionTetal(HyDistributionObject qo);

    void openEdits(Map map);

    String hnProportion();

    MembershipDistribution yzScDistribution(Integer types);

    Map<String,String> queryShareProp();

}
