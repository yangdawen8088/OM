package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zero2oneit.mall.goods.service.BargainRuleService;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@RestController
@RequestMapping("/admin/bargainRuleService")
public class BargainRuleController {

    @Autowired
    private BargainRuleService bargainRuleService;


    /**
     * 根据砍价规则获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody BargainRuleQueryObject qo) {
        return bargainRuleService.bargainList(qo);
    }


    /**
     * 添加或编辑砍价规则信息
     * @param bargainRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody BargainRule bargainRule){
        bargainRuleService.saveOrUpdate(bargainRule);
        return R.ok();
    }


    /**
     * 更改砍价规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(Integer status, String id){
        bargainRuleService.status(status, id);
        return R.ok();
    }


    /**
     * 删除砍价规则信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(@RequestBody String ids){
        return bargainRuleService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }


}
