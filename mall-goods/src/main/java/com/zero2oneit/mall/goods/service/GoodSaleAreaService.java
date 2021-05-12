package com.zero2oneit.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/2/24 15:56
 */
public interface GoodSaleAreaService extends IService<GoodSaleArea> {

    List<GoodSaleAreaTree> tree();

    BoostrapDataGrid pageList(GoodSaleAreaQueryObject qo);

    R addOrEdit(GoodSaleArea goodSaleArea);

    R status(String id);

    List<GoodSaleAreaTree> productPlaceTree();
}
