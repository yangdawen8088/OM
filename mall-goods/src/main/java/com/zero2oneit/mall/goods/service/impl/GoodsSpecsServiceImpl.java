package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.GoodsSpecsMapper;
import com.zero2oneit.mall.goods.service.GoodsSpecsService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Service
public class GoodsSpecsServiceImpl extends ServiceImpl<GoodsSpecsMapper, GoodsSpecs> implements GoodsSpecsService {

    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    @Override
    public BoostrapDataGrid pageList(SpecsQueryObject qo) {
        int total = goodsSpecsMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodsSpecsMapper.selectAll(qo));
    }

}