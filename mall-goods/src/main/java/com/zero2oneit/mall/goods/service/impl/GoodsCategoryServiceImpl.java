package com.zero2oneit.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.mapper.GoodsCategoryMapper;
import com.zero2oneit.mall.goods.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-06
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> categoryList() {
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.categoryList();
        return TreeUtils.assembleTree(goodsCategories);
    }

    @Override
    public BoostrapDataGrid pageList(GoodsCategoryQueryObject qo) {
        int total = goodsCategoryMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodsCategoryMapper.selectAll(qo));
    }

    @Override
    public void status(String id, Integer status) {
        goodsCategoryMapper.status(id, status);
    }

    @Override
    public Suggest getCategoryByName(String keyword) {
        return new Suggest(goodsCategoryMapper.getCategoryByName(keyword));
    }
}
