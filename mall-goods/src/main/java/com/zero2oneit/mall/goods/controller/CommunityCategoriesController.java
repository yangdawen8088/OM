package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.service.CommunityCategoriesService;
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
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/admin/communitiesCategory")
public class CommunityCategoriesController {

    @Autowired
    private CommunityCategoriesService categoryService;

    /**
     * 获取商品分类(社区)树集合
     * @return
     */
    @PostMapping("/tree")
    public List<CommunityCategories> tree() {
        return categoryService.categoryList();
    }

    /**
     * 根据商品分类IDS获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody CommunityCategoriesQueryObject qo) {
        return categoryService.pageList(qo);
    }

    /**
     * 添加或编辑商品分类（社区）信息
     * @param category
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody CommunityCategories category){
        categoryService.saveOrUpdate(category);
        return R.ok();
    }

    /**
     * 更改商品分类（社区）信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        categoryService.status(id, status);
        return R.ok();
    }

    /**
     * 初始化缓存
     * @return
     */
    @PostMapping("/init")
    public R init(){
        return categoryService.init();
    }

    /**
     * 根据分类名称匹配商品分类数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getCategoryByName")
    public Suggest getCategoryByName(@RequestBody String keyword) {
        return categoryService.getCategoryByName(keyword);
    }

}
