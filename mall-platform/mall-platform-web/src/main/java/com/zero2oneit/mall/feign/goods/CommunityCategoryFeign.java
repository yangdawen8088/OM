package com.zero2oneit.mall.feign.goods;

import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
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
public interface CommunityCategoryFeign {

    @PostMapping("/admin/communityCategory/addOrEdit")
    R addOrEdit(@RequestBody CommunityCategory category);

    @PostMapping("/admin/communityCategory/list")
    BoostrapDataGrid list(@RequestBody CommunityCategoryQueryObject qo);

    @PostMapping("/admin/communityCategory/deleteByIds")
    R deleteByIds(@RequestBody String ids);

    @PostMapping("/admin/communityCategory/getCategoryByName")
    Suggest getCategoryByName(@RequestBody String keyword);

}
