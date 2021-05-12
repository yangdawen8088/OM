package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.GoodsBrandMapper;
import com.zero2oneit.mall.goods.service.GoodsBrandService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-19
 */
@Service
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand> implements GoodsBrandService {

    @Autowired
    private GoodsBrandMapper goodsBrandMapper;

    @Override
    public BoostrapDataGrid pageList(GoodsBrandQueryObject qo) {
        int total = goodsBrandMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodsBrandMapper.selectAll(qo));
    }

    @Override
    public void status(String id, Integer status) {
        goodsBrandMapper.status(id, status);
    }

    @Override
    public Suggest getGoodsBrandByName(String keyword) {
        return new Suggest(goodsBrandMapper.getGoodsBrandByName(keyword));
    }

}