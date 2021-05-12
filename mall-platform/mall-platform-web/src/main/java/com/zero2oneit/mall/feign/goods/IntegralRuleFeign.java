package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.IntegralRule;
import com.zero2oneit.mall.common.query.goods.IntegralRuleQueryObject;
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
public interface IntegralRuleFeign {

    @PostMapping("/admin/integral/addOrEdit")
    R addOrEdit(@RequestBody IntegralRule integralRule);

    @PostMapping("/admin/integral/list")
    BoostrapDataGrid list(@RequestBody IntegralRuleQueryObject qo);

    @PostMapping("/admin/integral/deleteByIds")
    R deleteByIds(@RequestBody String ids);

}
