package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
public interface GoodsSpecsService extends IService<GoodsSpecs> {

    BoostrapDataGrid pageList(SpecsQueryObject qo);

}

