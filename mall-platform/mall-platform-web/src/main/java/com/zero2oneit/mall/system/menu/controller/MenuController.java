package com.zero2oneit.mall.system.menu.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.HomeMenu;
import com.zero2oneit.mall.system.menu.entity.Menu;
import com.zero2oneit.mall.system.menu.query.MenuQueryObject;
import com.zero2oneit.mall.system.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    //加载菜单所有数据
    @RequestMapping("/menuList")
    @ResponseBody
    public List<Menu> menuList() {
        return menuService.menuList();
    }

    //添加或编辑菜单信息
    @RequestMapping("/addOrEditMenu")
    @ResponseBody
    public R addOrEditDept(Menu menu) {//VO : value-object 值对象
        if (menu.getId().length() == 0){//添加
            menuService.add(menu);
        }else{//编辑
            menuService.edit(menu);
        }
        return R.ok("操作成功");
    }

    //注销菜单
    @RequestMapping("/delMenuByIds")
    @ResponseBody
    public R delMenuByIds(String menuIds, Integer menuStatus) {//VO : value-object 值对象
        menuService.delMenuByIds(menuIds, menuStatus);
        return R.ok("操作成功");
    }

    //根据菜单IDS获取对应的信息
    @RequestMapping("/getMenuByIds")
    @ResponseBody
    public BoostrapDataGrid getMenuByIds(@RequestBody MenuQueryObject qo) {//VO : value-object 值对象
        return menuService.queryList(qo);
    }


    //加载主页菜单
    @RequestMapping("/loadHomeMenus")
    @ResponseBody
    public R loadHomeMenus() {//VO : value-object 值对象
        return R.ok("操作成功", menuService.loadHomeMenus());
    }


    //加载主页菜单树版本
    @RequestMapping("/loadHomeTreeMenus")
    @ResponseBody
    public List<HomeMenu> loadHomeTreeMenus() {
        return menuService.loadHomeTreeMenus();
    }
    
}
