package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
public interface GroupRuleService extends IService<GroupRule> {

    BoostrapDataGrid pageList(GroupRuleQueryObject qo);

    R status(String id, Integer status);
}

