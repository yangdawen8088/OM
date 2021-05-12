package com.zero2oneit.mall.goods.api;

import com.zero2oneit.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/3/31
 */
@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询社区商品分类
     * @return
     */
    @RequestMapping("/categories")
    public R categories(){
        Object community = redisTemplate.opsForHash().get("om:goods:communityCategories", "community");
        return R.ok(community);
    }

}
