package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-06
 */
@RestController
@RequestMapping("/admin/goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 获取商品分类(全国)树集合
     * @return
     */
    @PostMapping("/tree")
    public List<GoodsCategory> tree() {
        return goodsCategoryService.categoryList();
    }

    /**
     * 根据商品分类IDS获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GoodsCategoryQueryObject qo) {
        return goodsCategoryService.pageList(qo);
    }

    /**
     * 添加或编辑商品分类（全国）信息
     * @param category
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GoodsCategory category){
        goodsCategoryService.saveOrUpdate(category);
        return R.ok();
    }

    /**
     * 更改商品分类（全国）信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        goodsCategoryService.status(id, status);
        return R.ok();
    }

    /**
     * 根据分类名称匹配商品分类数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getCategoryByName")
    public Suggest getCategoryByName(@RequestBody String keyword) {
        return goodsCategoryService.getCategoryByName(keyword);
    }

}
