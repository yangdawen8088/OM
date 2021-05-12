package com.zero2oneit.mall.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.mapper.CommunityCategoriesMapper;
import com.zero2oneit.mall.goods.mapper.CommunityCategoryMapper;
import com.zero2oneit.mall.goods.service.CommunityCategoriesService;
import com.zero2oneit.mall.goods.service.CommunityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Service
public class CommunityCategoriesServiceImpl extends ServiceImpl<CommunityCategoriesMapper, CommunityCategories> implements CommunityCategoriesService {

    @Autowired
    private CommunityCategoriesMapper categoryMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<CommunityCategories> categoryList() {
        List<CommunityCategories> goodsCategories = categoryMapper.categoryList();
        return TreeUtils.assembleTree(goodsCategories);
    }

    @Override
    public BoostrapDataGrid pageList(CommunityCategoriesQueryObject qo) {
        int total = categoryMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : categoryMapper.selectAll(qo));
    }

    @Override
    public void status(String id, Integer status) {
        categoryMapper.status(id, status);
    }

    @Override
    public Suggest getCategoryByName(String keyword) {
        return new Suggest(categoryMapper.getCategoryByName(keyword));
    }

    @Override
    public R init() {
        List<CommunityCategories> lists = categoryMapper.categorys();
        redisTemplate.opsForHash().put("om:goods:communityCategories", "community", JSON.toJSONString(lists));
        return R.ok("初始化成功");
    }

}