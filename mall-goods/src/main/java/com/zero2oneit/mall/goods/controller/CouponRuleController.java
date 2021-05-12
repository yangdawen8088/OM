package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.CouponRule;
import com.zero2oneit.mall.common.query.goods.CouponRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.CouponRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-19
 */
@RestController
@RequestMapping("/admin/coupon")
public class CouponRuleController {

    @Autowired
    private CouponRuleService couponRuleService;

    /**
     * 查询优惠券规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody CouponRuleQueryObject qo){
        return couponRuleService.pageList(qo);
    }

    /**
     * 添加或编辑优惠券规则信息
     * @param couponRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody CouponRule couponRule){
        couponRuleService.saveOrUpdate(couponRule);
        return R.ok();
    }

    /**
     * 根据ID删除优惠券规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(@RequestParam("ids") String ids){
        return couponRuleService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }

    /**
     * 更改优惠券则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(@RequestParam("id") String id, @RequestParam("status") Integer status){
        return couponRuleService.status(id, status);
    }

}
