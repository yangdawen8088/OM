package com.zero2oneit.mall.system.role.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.role.query.RoleQueryObject;
import com.zero2oneit.mall.system.role.service.RoleService;
import com.zero2oneit.mall.system.role.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/rolePage")
    @ResponseBody
    public BoostrapDataGrid rolePage(@RequestBody RoleQueryObject qo) {//VO : value-object 值对象
        return roleService.queryList(qo);
    }


    //添加或编辑角色信息
    @RequestMapping("/getAllRole")
    @ResponseBody
    public R getAllRole() {//VO : value-object 值对象
        return R.ok("操作成功", roleService.getAllRole());
    }

    //添加或编辑角色信息
    @RequestMapping("/addOrEditRole")
    @ResponseBody
    public R addOrEditRole(RoleVO roleVO) {//VO : value-object 值对象
        if (roleVO.getId() == null){//添加
            roleService.add(roleVO);
        }else{//编辑
            roleService.edit(roleVO);
        }
        return R.ok("操作成功");
    }

    //根据id删除角色信息
    @RequestMapping("/delRoleByIds")
    @ResponseBody
    public R delRoleByIds(String ids) {//VO : value-object 值对象
        roleService.delRoleByIds(ids);
        return R.ok("操作成功");
    }

}
