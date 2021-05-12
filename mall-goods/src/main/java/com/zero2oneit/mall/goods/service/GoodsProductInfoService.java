package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodsProductInfo;
import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
public interface GoodsProductInfoService extends IService<GoodsProductInfo> {

    BoostrapDataGrid pageList(GoodsProductInfoQueryObject qo);

    void status(String id, Integer status);

    void audit(String ids, Integer productStatus, String approveResult, String userName);

    Map<String, Object> load(String id);

    void copy(String id);

    List<Products> esList(String ids);

    void bindSecKill(String productIds, String ruleId);

    void unSeckill(String productIds);

}

