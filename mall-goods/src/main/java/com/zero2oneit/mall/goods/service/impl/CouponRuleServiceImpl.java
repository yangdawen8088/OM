package com.zero2oneit.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.CouponRule;
import com.zero2oneit.mall.common.query.goods.CouponRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.mapper.CouponRuleMapper;
import com.zero2oneit.mall.goods.service.CouponRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@Service
public class CouponRuleServiceImpl extends ServiceImpl<CouponRuleMapper, CouponRule> implements CouponRuleService {

    @Autowired
    private CouponRuleMapper couponRuleMapper;

    @Override
    public BoostrapDataGrid pageList(CouponRuleQueryObject qo) {
        int total = couponRuleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : couponRuleMapper.selectAll(qo));
    }

    @Override
    public R status(String id, Integer status) {
        CouponRule couponRule = new CouponRule();
        couponRule.setId(Long.valueOf(id));
        if (status==1){
            couponRule.setRuleStatus(2);
        }else if (status==2){
            couponRule.setRuleStatus(1);
        }
        return couponRuleMapper.updateById(couponRule)>0? R.ok():R.fail();
    }
}