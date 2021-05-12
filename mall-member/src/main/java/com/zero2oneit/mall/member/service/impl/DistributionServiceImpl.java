package com.zero2oneit.mall.member.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.query.member.DistributionObject;
import com.zero2oneit.mall.common.bean.member.MechanismDistribute;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.DistributionMapper;
import com.zero2oneit.mall.member.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/**
 * Description:
 *
 * @author Atzel
 * @date 2020/9/2 15:21
 */
@Service
public class DistributionServiceImpl extends ServiceImpl<DistributionMapper, MechanismDistribute> implements DistributionService {

    @Autowired
    private DistributionMapper distributionMapper;



    @Override
    public BoostrapDataGrid DistributionList(DistributionObject qo) {
        int total= distributionMapper.total(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : distributionMapper.DistributionListRows(qo)) ;
    }

    @Override
    public BigDecimal getProportion() {
        return distributionMapper.getProportion();
    }

    @Override
    public void marketKgEdit(Map map) {
        distributionMapper.marketKgEdit(map);
    }

    @Override
    public void postKgEdit(Map map) {
        distributionMapper.postKgEdit(map);
    }

    @Override
    public void migrantKgEdit(Map map) {
        distributionMapper.migrantKgEdit(map);
    }

    @Override
    public MechanismDistribute commissionInquire() {
        return distributionMapper.commissionInquire();
    }

    @Override
    public MechanismDistribute commissionSwitch() {
        return distributionMapper.commissionSwitch();
    }

    @Override
    public MechanismDistribute memberSwitch() {
        return distributionMapper.memberSwitch();
    }

    @Override
    public MechanismDistribute withdrawalSwitch() {
        return distributionMapper.withdrawalSwitch();
    }

    @Override
    public MechanismDistribute sharingCommissionRules() {
        return distributionMapper.sharingCommissionRules();
    }


}
