package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.bean.goods.IntegralRule;
import com.zero2oneit.mall.common.query.goods.IntegralRuleQueryObject;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.IntegralRuleFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 远程调用商品规格服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/2
 */
@RestController
@RequestMapping("/remote/integral")
@CrossOrigin
public class IntegralRuleRemote {

    @Autowired
    private IntegralRuleFeign ruleFeign;

    /**
     * 积分规则列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody IntegralRuleQueryObject qo){
        return ruleFeign.list(qo);
    }

    /**
     * 添加或编辑积分规则信息
     * @param integralRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(IntegralRule integralRule){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        integralRule.setOperatorName(user.getUsername());
        return ruleFeign.addOrEdit(integralRule);
    }

    /**
     * 删除积分规则信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R delAdvertByIds(String ids){
        return ruleFeign.deleteByIds(ids);
    }

}
