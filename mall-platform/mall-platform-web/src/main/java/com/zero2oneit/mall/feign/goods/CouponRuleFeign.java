package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.CouponRule;
import com.zero2oneit.mall.common.query.goods.CouponRuleQueryObject;
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
 * @date 2021/3/17
 */
@FeignClient("goods-service")
public interface CouponRuleFeign {

    @PostMapping("/admin/coupon/list")
    BoostrapDataGrid list(@RequestBody CouponRuleQueryObject qo);

    @PostMapping("/admin/coupon/addOrEdit")
    R addOrEdit(@RequestBody CouponRule couponRule);

    @PostMapping("/admin/coupon/delByIds")
    R delByIds(@RequestParam("ids") String ids);

    @PostMapping("/admin/coupon/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);
}
