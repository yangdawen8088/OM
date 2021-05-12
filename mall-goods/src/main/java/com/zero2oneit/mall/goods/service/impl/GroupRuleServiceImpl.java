package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.GroupRuleMapper;
import com.zero2oneit.mall.goods.service.GroupRuleService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@Service
public class GroupRuleServiceImpl extends ServiceImpl<GroupRuleMapper, GroupRule> implements GroupRuleService {

    @Autowired
    private GroupRuleMapper groupRuleMapper;

    @Override
    public BoostrapDataGrid pageList(GroupRuleQueryObject qo) {
        int total = groupRuleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : groupRuleMapper.selectAll(qo));
    }

    @Override
    public R status(String id, Integer status) {
        GroupRule groupRule = new GroupRule();
        groupRule.setId(Long.valueOf(id));
        if (status==1){
            groupRule.setRuleStatus(2);
        }else if (status==2){
            groupRule.setRuleStatus(1);
        }
        return groupRuleMapper.updateById(groupRule)>0?R.ok():R.fail();
    }


}