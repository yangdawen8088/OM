package com.zero2oneit.mall.service.business.remote;

import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.feign.business.CommunityBusinessFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 远程调用商品分类（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/communityBusiness")
@CrossOrigin
public class CommunityBusinessRemote {

    @Autowired
    private CommunityBusinessFeign communityBusinessFeign;

    /**
     * 根据商家名称匹配商家数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getBusinessByName")
    public Suggest getBusinessByName(String keyword) {//VO : value-object 值对象
        return communityBusinessFeign.getBusinessByName(keyword);
    }

}
