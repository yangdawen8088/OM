package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@FeignClient("goods-service")
public interface SpecsFeign {

    @PostMapping("/admin/specs/addOrEdit")
    R addOrEdit(@RequestBody GoodsSpecs goodsSpecs);

    @PostMapping("/admin/specs/list")
    BoostrapDataGrid list(@RequestBody SpecsQueryObject qo);

    @PostMapping("/admin/specs/deleteByIds")
    R deleteByIds(@RequestBody String ids);

}
