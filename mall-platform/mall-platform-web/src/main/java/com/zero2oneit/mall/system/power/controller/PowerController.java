package com.zero2oneit.mall.system.power.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.system.common.shiro.util.ShiroUtils;
import com.zero2oneit.mall.system.power.entity.DeptUser;
import com.zero2oneit.mall.system.power.service.PowerService;
import com.zero2oneit.mall.system.power.vo.PowerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PowerController {

    @Autowired
    private PowerService powerService;

    //加载部门用户数据
    @RequestMapping("/deptUsersList")
    @ResponseBody
    public List<DeptUser> deptUsersList() {
        return powerService.deptUsersList();
    }

    //保存用户或角色权限
    @RequestMapping("/saveUserOrRoleFunction")
    @ResponseBody
    public R saveUserOrRoleFunction(PowerVO vo) {//VO : value-object 值对象
        ShiroUtils.clearCachedAuthorizationInfo();
        powerService.saveUserOrRoleFunction(vo);
        return R.ok("操作成功");
    }

    //加载用户或角色权限
    @RequestMapping("/loadUserOrRoleFunction")
    @ResponseBody
    public R loadUserOrRoleFunction(PowerVO vo) {//VO : value-object 值对象
        return R.ok("操作成功", powerService.loadUserOrRoleFunction(vo));
    }

    //保存用户或角色数据权限
    @RequestMapping("/saveUserOrRoleData")
    @ResponseBody
    public R saveUserOrRoleData(PowerVO vo) {//VO : value-object 值对象
        powerService.saveUserOrRoleData(vo);
        return R.ok("操作成功");
    }

    //加载用户或角色数据权限
    @RequestMapping("/loadUserOrRoleData")
    @ResponseBody
    public R loadUserOrRoleData(PowerVO vo) {//VO : value-object 值对象
        return R.ok("操作成功", powerService.loadUserOrRoleData(vo));
    }
    
}
