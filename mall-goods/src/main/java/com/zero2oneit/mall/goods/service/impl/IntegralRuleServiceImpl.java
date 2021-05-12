package com.zero2oneit.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.IntegralRule;
import com.zero2oneit.mall.common.query.goods.IntegralRuleQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.mapper.IntegralRuleMapper;
import com.zero2oneit.mall.goods.service.IntegralRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Service
public class IntegralRuleServiceImpl extends ServiceImpl<IntegralRuleMapper, IntegralRule> implements IntegralRuleService {

    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public BoostrapDataGrid pageList(IntegralRuleQueryObject qo) {
        int total = integralRuleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : integralRuleMapper.selectAll(qo));
    }

}