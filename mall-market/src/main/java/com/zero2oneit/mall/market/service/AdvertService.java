package com.zero2oneit.mall.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-01-25
 */
public interface AdvertService extends IService<Advert> {

    BoostrapDataGrid pageList(AdvertQueryObject qo);

    /**
     * 添加广告
     * @param advert
     * @return
     */
    Advert add(Advert advert);

}

