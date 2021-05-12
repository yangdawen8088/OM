package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-06
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {

    List<GoodsCategory> categoryList();

    BoostrapDataGrid pageList(GoodsCategoryQueryObject qo);

    void status(String id, Integer status);

    Suggest getCategoryByName(String keyword);
}

