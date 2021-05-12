package com.zero2oneit.mall.market.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.market.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-01-25
 */
@RestController
@RequestMapping("/admin/advert")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询广告列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody AdvertQueryObject qo){
        return advertService.pageList(qo);
    }

    /**
     * 添加或编辑广告信息
     * @param advert
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody Advert advert){

        if(advert.getId() == null){
            advert = advertService.add(advert);
        }else {
            advertService.saveOrUpdate(advert);
        }

        //根据类型查询设置到
        QueryWrapper<Advert> wrapper = new QueryWrapper();
        wrapper.eq("advert_position", advert.getAdvertPosition());
        List<Advert> adverts = advertService.list(wrapper);
        redisTemplate.opsForValue().set("om:market:advert"+advert.getAdvertPosition(), JSON.toJSONString(adverts));
        return R.ok();
    }

    /**
     * 根据ID删除对应的广告信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(String ids, String advertPosition){

        boolean b = advertService.removeByIds(Arrays.asList(ids.split(",")));

        QueryWrapper<Advert> wrapper = new QueryWrapper();
        wrapper.eq("advert_position", advertPosition);
        List<Advert> adverts = advertService.list(wrapper);
        if(adverts.size() > 0){
           redisTemplate.opsForValue().set("om:market:advert"+advertPosition, JSON.toJSONString(adverts));
        }

        return b == true ? R.ok("删除成功") : R.fail("删除失败");
    }

}
