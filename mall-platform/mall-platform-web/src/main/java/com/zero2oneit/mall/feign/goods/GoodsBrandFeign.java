package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@FeignClient("goods-service")
public interface GoodsBrandFeign {

    @PostMapping("/admin/goodsBrand/addOrEdit")
    R addOrEdit(@RequestBody GoodsBrand brand);

    @PostMapping("/admin/goodsBrand/list")
    BoostrapDataGrid list(@RequestBody GoodsBrandQueryObject qo);

    @PostMapping("/admin/goodsBrand/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);

    @PostMapping("/admin/goodsBrand/getGoodsBrandByName")
    Suggest getGoodsBrandByName(String keyword);
}
