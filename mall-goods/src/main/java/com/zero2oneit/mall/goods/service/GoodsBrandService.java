package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-19
 */
public interface GoodsBrandService extends IService<GoodsBrand> {

    BoostrapDataGrid pageList(GoodsBrandQueryObject qo);

    void status(String id, Integer status);

    Suggest getGoodsBrandByName(String keyword);

}

