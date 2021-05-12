package com.zero2oneit.mall.goods.service.impl;

import com.zero2oneit.mall.common.bean.goods.GoodsProductInfoRelation;
import com.zero2oneit.mall.goods.mapper.GoodsProductInfoRelationMapper;
import com.zero2oneit.mall.goods.service.GoodsProductInfoRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Service
public class GoodsProductInfoRelationServiceImpl extends ServiceImpl<GoodsProductInfoRelationMapper, GoodsProductInfoRelation> implements GoodsProductInfoRelationService {

    @Autowired
    private GoodsProductInfoRelationMapper goodsProductInfoRelationMapper;

}