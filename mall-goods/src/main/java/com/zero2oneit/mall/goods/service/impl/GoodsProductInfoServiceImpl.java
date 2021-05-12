package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GoodsProductInfo;
import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.snowflake.IdWorker;
import com.zero2oneit.mall.common.utils.snowflake.IdWorkerFactory;
import com.zero2oneit.mall.goods.mapper.GoodsProductInfoMapper;
import com.zero2oneit.mall.goods.service.GoodsProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Service
public class GoodsProductInfoServiceImpl extends ServiceImpl<GoodsProductInfoMapper, GoodsProductInfo> implements GoodsProductInfoService {

    @Autowired
    private GoodsProductInfoMapper goodsProductInfoMapper;

    @Override
    public BoostrapDataGrid pageList(GoodsProductInfoQueryObject qo) {
        int total = goodsProductInfoMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodsProductInfoMapper.selectAll(qo));
    }

    @Override
    public void status(String id, Integer status) {
        goodsProductInfoMapper.status(id, status);
    }

    @Override
    public void audit(String ids, Integer productStatus, String approveResult, String userName) {
        goodsProductInfoMapper.audit(ids, productStatus, approveResult, userName);
    }

    @Override
    public Map<String, Object> load(String id) {
        return goodsProductInfoMapper.load(id);
    }

    @Override
    public void copy(String id) {
        IdWorker idworker = IdWorkerFactory.create(0,0);
        long nextId = idworker.nextId();
        goodsProductInfoMapper.copyGoods(nextId, id);
        goodsProductInfoMapper.copyGoodsRelation(nextId, id);
    }

    @Override
    public List<Products> esList(String ids) {
        return goodsProductInfoMapper.esList(ids);
    }

    @Override
    public void bindSecKill(String productIds, String ruleId) {
        goodsProductInfoMapper.bindSecKill(productIds, ruleId);
    }

    @Override
    public void unSeckill(String productIds) {
        goodsProductInfoMapper.unSeckill(productIds);
    }

}