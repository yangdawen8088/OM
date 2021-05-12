package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.BargainRuleMapper;
import com.zero2oneit.mall.goods.service.BargainRuleService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@Service
public class BargainRuleServiceImpl extends ServiceImpl<BargainRuleMapper, BargainRule> implements BargainRuleService {

    @Autowired
    private BargainRuleMapper bargainRuleMapper;


    @Override
    public BoostrapDataGrid bargainList(BargainRuleQueryObject qo) {
        int total=bargainRuleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST :bargainRuleMapper.selectAll(qo));
    }

    @Override
    public void status(Integer status,String id) {
        bargainRuleMapper.status(status,id);
    }
}
