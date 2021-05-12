package com.zero2oneit.mall.feign.business;

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
@FeignClient("business-service")
public interface CommunityBusinessFeign {

    @PostMapping("/admin/communityBusiness/getBusinessByName")
    Suggest getBusinessByName(@RequestBody String keyword);

}
