package com.zero2oneit.mall.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.query.member.DistributionObject;
import com.zero2oneit.mall.common.bean.member.MechanismDistribute;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


import java.math.BigDecimal;
import java.util.Map;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/9/2 15:19
 */
public interface DistributionService extends IService<MechanismDistribute> {

    BoostrapDataGrid DistributionList(DistributionObject qo);

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
