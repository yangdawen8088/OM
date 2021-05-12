package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.service.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-19
 */
@RestController
@RequestMapping("/admin/goodsBrand")
public class GoodsBrandController {

    @Autowired
    private GoodsBrandService goodsBrandService;

    /**
     * 根据商品分类IDS获取对应的品牌列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsBrandQueryObject qo) {
        return goodsBrandService.pageList(qo);
    }

    /**
     * 添加或编辑商品品牌（全国）信息
     * @param brand
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodsBrand brand){
        goodsBrandService.saveOrUpdate(brand);
        return R.ok();
    }

    /**
     * 更改商品品牌（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        goodsBrandService.status(id, status);
        return R.ok();
    }

    /**
     * 根据品牌名称匹配品牌数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getGoodsBrandByName")
    public Suggest getGoodsBrandByName(@RequestBody String keyword) {
        return goodsBrandService.getGoodsBrandByName(keyword);
    }

}
