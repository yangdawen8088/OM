package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.SeckillRuleFeign;
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
@RequestMapping("/remote/seckill")
@CrossOrigin
public class SeckillRuleRemote {

    @Autowired
    private SeckillRuleFeign ruleFeign;

    /**
     * 查询秒杀规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody SeckillRuleQueryObject qo){
        return ruleFeign.list(qo);
    }

    /**
     * 添加或编辑秒杀规则信息
     * @param seckillRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(SeckillRule seckillRule){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        seckillRule.setOperatorName(user.getUsername());
        return ruleFeign.addOrEdit(seckillRule);
    }

    /**
     * 根据ID删除秒杀规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(String ids){
        return ruleFeign.delByIds(ids);
    }

    /**
     * 更改秒杀规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        return ruleFeign.status(id, status);
    }

}
