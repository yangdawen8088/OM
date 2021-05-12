package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/3/17
 */
@FeignClient("goods-service")
public interface BargainRuleFeign {


    @PostMapping("/admin/bargainRuleService/list")
    BoostrapDataGrid list(@RequestBody BargainRuleQueryObject qo);


    @PostMapping("/admin/bargainRuleService/addOrEdit")
    R addOrEdit(@RequestBody BargainRule bargainRule);


    @PostMapping("/admin/bargainRuleService/status")
    public R status(@RequestParam("status") Integer status,@RequestParam("id") String id);


    @PostMapping("/admin/bargainRuleService/deleteByIds")
    public R deleteByIds(@RequestBody String ids);

}
