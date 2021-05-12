package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.bean.goods.GoodsProductInfo;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.apache.ibatis.annotations.Param;
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
public interface CommunityGoodsFeign {

    @PostMapping("/admin/communityGoods/addOrEdit")
    R addOrEdit(@RequestBody GoodsProductInfo goods);

    @PostMapping("/admin/communityGoods/list")
    BoostrapDataGrid list(@RequestBody GoodsProductInfoQueryObject qo);

    @PostMapping("/admin/goodSaleArea/productPlaceTree")
    List<GoodSaleAreaTree> productPlaceTree();

    @PostMapping("/admin/communityGoods/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);

    @PostMapping("/admin/communityGoods/audit")
    R audit(@RequestParam("ids") String ids, @RequestParam("productStatus") Integer productStatus, @RequestParam("approveResult") String approveResult, @RequestParam("userName") String userName);

    @PostMapping("/admin/communityGoods/load")
    R load(@RequestParam("id") String id);

    @PostMapping("/admin/communityGoods/copy")
    R copy(@RequestParam("id") String id);

    @PostMapping("/admin/communityGoods/bindSecKill")
    R bindSecKill(@RequestParam("productIds") String productIds, @RequestParam("ruleId") String ruleId);

    @PostMapping("/admin/communityGoods/unSeckill")
    R unSeckill(@RequestParam("productIds") String productIds);
}
