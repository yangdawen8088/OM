package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@FeignClient("goods-service")
public interface GoodsFeign {

    @PostMapping("/admin/goods/addOrEdit")
    R addOrEdit(@RequestBody GoodsSpu goods);

    @PostMapping("/admin/goods/report")
    Map<String, Object> report();

    @PostMapping("/admin/goods/list")
    BoostrapDataGrid list(GoodsSpuQueryObject qo);

    @PostMapping("/admin/goods/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);

    @PostMapping("/admin/goods/audit")
    R audit(@RequestParam("ids") String ids, @RequestParam("productStatus") Integer productStatus, @RequestParam("approveResult") String approveResult, @RequestParam("userName") String userName);

    @PostMapping("/admin/goods/load")
    R load(@RequestParam("id") String id);

    @PostMapping("/admin/goods/loadSku")
    List<Map<String, Object>> loadSku(@RequestParam("id") String id);
}
