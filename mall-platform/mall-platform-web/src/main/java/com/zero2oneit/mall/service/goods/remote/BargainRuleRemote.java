package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.BargainRuleFeign;
import com.zero2oneit.mall.feign.goods.SeckillRuleFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Description: 远程调用商品分类（社区团购）服务
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/5
 */
@RestController
@RequestMapping("/remote/bargain")
@CrossOrigin
public class BargainRuleRemote {

    @Autowired
    private BargainRuleFeign ruleFeign;

    /**
     * 根据砍价规则信息获取对应的列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody BargainRuleQueryObject qo) {
        return ruleFeign.list(qo);
    }


    /**
     * 添加或编辑砍价规则信息
     * @param bargainRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(BargainRule bargainRule){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        bargainRule.setOperatorName(user.getUsername());
        return  ruleFeign.addOrEdit(bargainRule);
    }


    /**
     * 更改砍价规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(Integer status, String id){
        return  ruleFeign.status(status, id);
    }


    /**
     * 删除砍价规则信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public R deleteByIds(String ids){
        return ruleFeign.deleteByIds(ids);
    }


}
