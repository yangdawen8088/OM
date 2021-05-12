package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
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
public interface GroupRuleFeign {

    @PostMapping("/admin/group/list")
    BoostrapDataGrid list(@RequestBody GroupRuleQueryObject qo);

    @PostMapping("/admin/group/addOrEdit")
    R addOrEdit(@RequestBody GroupRule groupRule);

    @PostMapping("/admin/group/delByIds")
    R delByIds(@RequestParam("ids") String ids);

    @PostMapping("/admin/group/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);
}
