package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-08
 */
public interface GoodsSpuService extends IService<GoodsSpu> {

    Map<String, Object> report();

    BoostrapDataGrid pageList(GoodsSpuQueryObject qo);

    void status(String id, Integer status);

    void audit(String ids, Integer productStatus, String approveResult, String userName);

    Map<String, Object> load(String id);

    List<Map<String, Object>> loadSku(String id);
}

