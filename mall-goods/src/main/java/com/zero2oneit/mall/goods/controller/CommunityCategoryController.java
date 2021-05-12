package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.goods.service.CommunityCategoryService;
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
@RequestMapping("/admin/communityCategory")
public class CommunityCategoryController {

    @Autowired
    private CommunityCategoryService categoryService;

    /**
     * 查询商品分类（社区团购）列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody CommunityCategoryQueryObject qo){
        return categoryService.pageList(qo);
    }

    /**
     * 添加或编辑商品分类（社区团购）信息
     * @param category
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody CommunityCategory category){
        categoryService.saveOrUpdate(category);
        return R.ok();
    }

    /**
     * 删除商品分类（社区团购）信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(@RequestBody String ids){
        return categoryService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
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
