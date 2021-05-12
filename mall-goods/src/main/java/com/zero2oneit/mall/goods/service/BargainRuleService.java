package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
public interface BargainRuleService extends IService<BargainRule> {

    BoostrapDataGrid bargainList(BargainRuleQueryObject qo);

    void status(Integer status,String id);
}

