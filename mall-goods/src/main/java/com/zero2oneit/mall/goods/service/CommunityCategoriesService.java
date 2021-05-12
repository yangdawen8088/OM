package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
public interface CommunityCategoriesService extends IService<CommunityCategories> {

    List<CommunityCategories> categoryList();

    BoostrapDataGrid pageList(CommunityCategoriesQueryObject qo);

    void status(String id, Integer status);

    Suggest getCategoryByName(String keyword);

    R init();

}

