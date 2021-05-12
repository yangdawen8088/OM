package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@FeignClient("goods-service")
public interface GoodSaleAreaFeign {

    @PostMapping("/admin/goodSaleArea/tree")
    List<GoodSaleAreaTree> tree();

    @PostMapping("/admin/goodSaleArea/list")
    BoostrapDataGrid list(@RequestBody GoodSaleAreaQueryObject qo);

    @PostMapping("/admin/goodSaleArea/addOrEdit")
    R addOrEdit(@RequestBody GoodSaleArea goodSaleArea);

    @PostMapping("/admin/goodSaleArea/status")
    R status(@RequestParam("id") String id);
}
