package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.SeckillRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/admin/seckill")
public class SeckillRuleController {

    @Autowired
    private SeckillRuleService seckillRuleService;

    /**
     * 查询秒杀规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody SeckillRuleQueryObject qo){
        return seckillRuleService.pageList(qo);
    }

    /**
     * 添加或编辑秒杀规则信息
     * @param seckillRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody SeckillRule seckillRule){
        seckillRuleService.saveOrUpdate(seckillRule);
        return R.ok();
    }

    /**
     * 根据ID删除秒杀规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(@RequestParam("ids") String ids){
        return seckillRuleService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }

    /**
     * 更改秒杀规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(@RequestParam("id") String id, @RequestParam("status") Integer status){
        return seckillRuleService.status(id, status);
    }

}
