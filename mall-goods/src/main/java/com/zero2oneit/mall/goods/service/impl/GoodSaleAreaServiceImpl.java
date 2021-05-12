package com.zero2oneit.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.snowflake.IdWorker;
import com.zero2oneit.mall.common.utils.snowflake.StardardIdWorker;
import com.zero2oneit.mall.goods.mapper.GoodSaleAreaMapper;
import com.zero2oneit.mall.goods.service.GoodSaleAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/2/24 15:56
 */
@Service
public class GoodSaleAreaServiceImpl extends ServiceImpl<GoodSaleAreaMapper,GoodSaleArea> implements GoodSaleAreaService{

    @Autowired
    private GoodSaleAreaMapper goodSaleAreaMapper;

    @Override
    public List<GoodSaleAreaTree> tree() {
        return TreeUtils.assembleTree(goodSaleAreaMapper.tree());
    }

    @Override
    public BoostrapDataGrid pageList(GoodSaleAreaQueryObject qo) {
        int total = goodSaleAreaMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : goodSaleAreaMapper.selectAll(qo));
    }

    @Override
    public R addOrEdit(GoodSaleArea goodSaleArea) {
        if (ObjectUtils.isEmpty(goodSaleArea.getId())){
            IdWorker IdWorker = new StardardIdWorker();
            goodSaleArea.setId(IdWorker.nextId());
            return goodSaleAreaMapper.insert(goodSaleArea)>0?R.ok():R.fail() ;
        }else {
            return goodSaleAreaMapper.updateById(goodSaleArea)>0?R.ok():R.fail();
        }
    }

    @Override
    public R status(String id) {
        GoodSaleArea goodSaleArea=new GoodSaleArea();
        goodSaleArea.setStatus(0);
        goodSaleArea.setId(Long.valueOf(id));
        return goodSaleAreaMapper.updateById(goodSaleArea)>0?R.ok():R.fail();
    }

    @Override
    public List<GoodSaleAreaTree> productPlaceTree() {
        return TreeUtils.assembleTree(goodSaleAreaMapper.productPlaceTree());
    }
}
