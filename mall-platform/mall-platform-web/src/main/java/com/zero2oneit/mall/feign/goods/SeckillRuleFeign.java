package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
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
public interface SeckillRuleFeign {

    @PostMapping("/admin/seckill/list")
    BoostrapDataGrid list(@RequestBody SeckillRuleQueryObject qo);

    @PostMapping("/admin/seckill/addOrEdit")
    R addOrEdit(@RequestBody SeckillRule seckillRule);

    @PostMapping("/admin/seckill/delByIds")
    R delByIds(@RequestParam("ids") String ids);

    @PostMapping("/admin/seckill/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);
}
