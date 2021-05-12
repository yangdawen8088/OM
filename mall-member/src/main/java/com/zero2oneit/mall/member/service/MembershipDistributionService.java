package com.zero2oneit.mall.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.query.member.HyDistributionObject;
import com.zero2oneit.mall.common.bean.member.MembershipDistribution;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


import java.util.Map;

/**
 * @author Sinper
 * @create 2020-12-18
 * @description
 */
public interface MembershipDistributionService extends IService<MembershipDistribution> {

    BoostrapDataGrid distributionList(HyDistributionObject qo);

    R openEdits(Map map);

    String hnProportion();

    MembershipDistribution yzScDistribution(Integer types);

    R queryShareProp();
}

