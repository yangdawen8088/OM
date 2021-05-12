package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
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
public interface GoodsCategoryFeign {

    @PostMapping("/admin/goodsCategory/addOrEdit")
    R addOrEdit(@RequestBody GoodsCategory category);

    @PostMapping("/admin/goodsCategory/list")
    BoostrapDataGrid list(@RequestBody GoodsCategoryQueryObject qo);

    @PostMapping("/admin/goodsCategory/tree")
    List<GoodsCategory> tree();

    @PostMapping("/admin/goodsCategory/status")
    R status(@RequestParam("id") String id,@RequestParam("status") Integer status);

    @PostMapping("/admin/goodsCategory/getCategoryByName")
    Suggest getCategoryByName(@RequestBody String keyword);

}
