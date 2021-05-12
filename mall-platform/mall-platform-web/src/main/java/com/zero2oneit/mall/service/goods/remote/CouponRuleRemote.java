package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.bean.goods.CouponRule;
import com.zero2oneit.mall.common.query.goods.CouponRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.CouponRuleFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 远程调用商品分类（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/coupon")
@CrossOrigin
public class CouponRuleRemote {

    @Autowired
    private CouponRuleFeign ruleFeign;

    /**
     * 查询优惠劵规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody CouponRuleQueryObject qo){
        return ruleFeign.list(qo);
    }

    /**
     * 添加或编辑优惠劵规则信息
     * @param couponRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(CouponRule couponRule){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        couponRule.setOperatorName(user.getUsername());
        return ruleFeign.addOrEdit(couponRule);
    }

    /**
     * 根据ID删除优惠劵规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(String ids){
        return ruleFeign.delByIds(ids);
    }

    /**
     * 更改优惠劵规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        return ruleFeign.status(id, status);
    }

}
