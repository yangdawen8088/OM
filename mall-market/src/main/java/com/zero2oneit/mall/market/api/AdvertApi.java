package com.zero2oneit.mall.market.api;

import com.alibaba.fastjson.JSON;
import com.zero2oneit.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/24
 */
@RestController
@RequestMapping("/api/advert")
public class AdvertApi {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加载首页广告图片
     * @return
     */
    @PostMapping("/load")
    public R load(@RequestBody Map<String, String> maps){
        String data = redisTemplate.opsForValue().get("om:market:advert" + maps.get("type"));
        return R.ok("加载成功", JSON.parse(data));
    }

}
