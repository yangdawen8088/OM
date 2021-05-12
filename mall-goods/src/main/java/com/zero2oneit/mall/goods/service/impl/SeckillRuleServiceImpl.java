package com.zero2oneit.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.mapper.SeckillRuleMapper;
import com.zero2oneit.mall.goods.service.SeckillRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-17
 */
@Service
public class SeckillRuleServiceImpl extends ServiceImpl<SeckillRuleMapper, SeckillRule> implements SeckillRuleService {

    @Autowired
    private SeckillRuleMapper seckillRuleMapper;

    @Override
    public BoostrapDataGrid pageList(SeckillRuleQueryObject qo) {
        int total = seckillRuleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : seckillRuleMapper.selectAll(qo));
    }

    @Override
    public R status(String id, Integer status) {
        SeckillRule seckillRule = new SeckillRule();
        seckillRule.setId(Long.valueOf(id));
        if (status==1){
            seckillRule.setRuleStatus(2);
        }else if (status==2){
            seckillRule.setRuleStatus(1);
        }
        return seckillRuleMapper.updateById(seckillRule)>0?R.ok():R.fail();
    }


}