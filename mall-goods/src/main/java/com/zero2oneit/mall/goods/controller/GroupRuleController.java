package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.GroupRuleService;
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
@RequestMapping("/admin/group")
public class GroupRuleController {

    @Autowired
    private GroupRuleService groupRuleService;

    /**
     * 查询拼团规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GroupRuleQueryObject qo){
        return groupRuleService.pageList(qo);
    }

    /**
     * 添加或编辑拼团规则信息
     * @param groupRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody GroupRule groupRule){
        groupRuleService.saveOrUpdate(groupRule);
        return R.ok();
    }

    /**
     * 根据ID删除拼团规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(@RequestParam("ids") String ids){
        return groupRuleService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }

    /**
     * 更改拼团规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(@RequestParam("id") String id, @RequestParam("status") Integer status){
        return groupRuleService.status(id, status);
    }

}
