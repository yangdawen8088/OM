package com.zero2oneit.mall.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.market.mapper.AdvertMapper;
import com.zero2oneit.mall.market.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-01-25
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements AdvertService {

    @Autowired
    private AdvertMapper advertMapper;

    @Override
    public BoostrapDataGrid pageList(AdvertQueryObject qo) {
        int total = advertMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : advertMapper.selectAll(qo));
    }

    @Override
    public Advert add(Advert advert) {
        advertMapper.add(advert);
        return advertMapper.selectById(advert.getId());
    }

}