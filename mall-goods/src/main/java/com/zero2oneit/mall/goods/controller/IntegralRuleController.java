package com.zero2oneit.mall.goods.controller;

import com.zero2oneit.mall.common.bean.goods.IntegralRule;
import com.zero2oneit.mall.common.query.goods.IntegralRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.goods.service.IntegralRuleService;
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
@RequestMapping("/admin/integral")
public class IntegralRuleController {

    @Autowired
    private IntegralRuleService integralRuleService;

    /**
     * 积分规则列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody IntegralRuleQueryObject qo){
        return integralRuleService.pageList(qo);
    }

    /**
     * 添加或编辑积分规则信息
     * @param integralRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody IntegralRule integralRule){
        integralRuleService.saveOrUpdate(integralRule);
        return R.ok();
    }

    /**
     * 删除积分规则信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(@RequestBody String ids){
        return integralRuleService.removeByIds(Arrays.asList(ids.split(","))) == true ? R.ok("删除成功") : R.fail("删除失败");
    }

}
