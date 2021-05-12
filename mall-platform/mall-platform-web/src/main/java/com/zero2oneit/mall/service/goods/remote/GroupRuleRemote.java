package com.zero2oneit.mall.service.goods.remote;

import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.goods.GroupRuleFeign;
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
@RequestMapping("/remote/group")
@CrossOrigin
public class GroupRuleRemote {

    @Autowired
    private GroupRuleFeign ruleFeign;

    /**
     * 查询拼团规则列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody GroupRuleQueryObject qo){
        return ruleFeign.list(qo);
    }

    /**
     * 添加或编辑拼团规则信息
     * @param groupRule
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(GroupRule groupRule){
        User user = UserContext.getCurrentUser(UserContext.SYS_USER);
        groupRule.setOperatorName(user.getUsername());
        return ruleFeign.addOrEdit(groupRule);
    }

    /**
     * 根据ID删除拼团规则信息
     * @param ids
     * @return
     */
    @PostMapping("/delByIds")
    public R delByIds(String ids){
        return ruleFeign.delByIds(ids);
    }

    /**
     * 更改拼团规则信息状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    public R status(String id, Integer status){
        return ruleFeign.status(id, status);
    }

}
