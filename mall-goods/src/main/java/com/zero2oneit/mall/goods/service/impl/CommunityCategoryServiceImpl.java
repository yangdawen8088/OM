package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.CommunityCategoryMapper;
import com.zero2oneit.mall.goods.service.CommunityCategoryService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Service
public class CommunityCategoryServiceImpl extends ServiceImpl<CommunityCategoryMapper, CommunityCategory> implements CommunityCategoryService {

    @Autowired
    private CommunityCategoryMapper categoryMapper;

    @Override
    public BoostrapDataGrid pageList(CommunityCategoryQueryObject qo) {
        int total = categoryMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : categoryMapper.selectAll(qo));
    }

    @Override
    public Suggest getCategoryByName(String keyword) {
        return new Suggest(categoryMapper.getCategoryByName(keyword));
    }

}