package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.GoodsSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/admin/specs")
public class GoodsSpecsController {

    @Autowired
    private GoodsSpecsService goodsSpecsService;

    /**
     * 商品规格列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody SpecsQueryObject qo){
        return goodsSpecsService.pageList(qo);
    }

    /**
     * 添加或编辑商品规格信息
     * @param goodsSpecs
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodsSpecs goodsSpecs){
        goodsSpecsService.saveOrUpdate(goodsSpecs);
        return R.ok();
    }

    /**
     * 删除商品规格信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(@RequestBody String ids){
        return goodsSpecsService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }

}
