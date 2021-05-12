package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-17
 */
public interface SeckillRuleService extends IService<SeckillRule> {

    BoostrapDataGrid pageList(SeckillRuleQueryObject qo);

    R status(String id, Integer status);
}

