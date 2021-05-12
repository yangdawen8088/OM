package com.zero2oneit.mall.feign.market;

import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/3
 */
@FeignClient("market-service")
public interface AdvertFeign {

    @PostMapping("/admin/advert/addOrEdit")
    R addOrEdit(@RequestBody Advert advert);

    @PostMapping("/admin/advert/list")
    BoostrapDataGrid list(@RequestBody AdvertQueryObject qo);

    @PostMapping("/admin/advert/deleteByIds")
    R deleteByIds(@RequestParam("ids") String ids, @RequestParam("advertPosition") String advertPosition);

}
