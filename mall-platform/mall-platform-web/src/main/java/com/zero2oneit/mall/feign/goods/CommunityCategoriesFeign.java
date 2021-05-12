package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
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
public interface CommunityCategoriesFeign {

    @PostMapping("/admin/communitiesCategory/addOrEdit")
    R addOrEdit(@RequestBody CommunityCategories category);

    @PostMapping("/admin/communitiesCategory/list")
    BoostrapDataGrid list(@RequestBody CommunityCategoriesQueryObject qo);

    @PostMapping("/admin/communitiesCategory/tree")
    List<CommunityCategories> tree();

    @PostMapping("/admin/communitiesCategory/status")
    R status(@RequestParam("id") String id, @RequestParam("status") Integer status);

    @PostMapping("/admin/communitiesCategory/getCategoryByName")
    Suggest getCategoryByName(@RequestBody String keyword);

    @PostMapping("/admin/communitiesCategory/init")
    R init();

}
