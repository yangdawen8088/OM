package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.goods.mapper.GoodsSpuMapper;
import com.zero2oneit.mall.goods.service.GoodsSpuService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-08
 */
@Service
public class GoodsSpuServiceImpl extends ServiceImpl<GoodsSpuMapper, GoodsSpu> implements GoodsSpuService {

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    @Override
    public Map<String, Object> report() {
        return goodsSpuMapper.report();
    }

    @Override
    public BoostrapDataGrid pageList(GoodsSpuQueryObject qo) {
        int total = goodsSpuMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodsSpuMapper.selectAll(qo));
    }

    @Override
    public void status(String id, Integer status) {
        goodsSpuMapper.status(id, status);
    }

    @Override
    public void audit(String ids, Integer productStatus, String approveResult, String userName) {
        goodsSpuMapper.audit(ids, productStatus, approveResult, userName);
    }

    @Override
    public Map<String, Object> load(String id) {
        return goodsSpuMapper.load(id);
    }

    @Override
    public List<Map<String, Object>> loadSku(String id) {
        return goodsSpuMapper.loadSku(id);
    }

}