package com.zero2oneit.mall.system.menu.service.impl;

import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.HomeMenu;
import com.zero2oneit.mall.common.utils.tree.HomeMenuTreeUtils;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.menu.entity.Menu;
import com.zero2oneit.mall.system.menu.mapper.MenuMapper;
import com.zero2oneit.mall.system.menu.query.MenuQueryObject;
import com.zero2oneit.mall.system.menu.service.MenuService;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> menuList() {
        List<Menu> menuList = menuMapper.selectMenuList();
        //将原数据转换成树形结构的数据格式
        return TreeUtils.assembleTree(menuList);
    }

    @Override
    public void add(Menu menu) {
        //添加一级菜单
        if (menu.getMenuLevel() == 1) {
            menu.setId(null);
            menuMapper.addOneLevelMenu(menu);
        }else{//添加二级、三级菜单
            //先添加功能表
            menuMapper.addFunction(menu);
            //再添加菜单表
            menuMapper.addOneLevelMenu(menu);
        }
    }

    @Override
    public void edit(Menu menu) {
        menuMapper.edit(menu);
    }

    @Override
    public void delMenuByIds(String ids, Integer menuStatus) {
        menuMapper.delMenuByIds(ids, menuStatus);
    }

    @Override
    public BoostrapDataGrid queryList(MenuQueryObject qo) {
        //查询总记录数
        int total = menuMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : menuMapper.selectRows(qo));
    }

    @Override
    public List<HashMap<String, Object>> loadHomeMenus() {
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        return menuMapper.loadHomeMenus(currentUser.getId());
    }

    @Override
    public List<HomeMenu> loadHomeTreeMenus() {
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        List<HomeMenu> menuList = menuMapper.loadHomeTreeMenu(currentUser.getId());
        //将原数据转换成树形结构的数据格式
        return HomeMenuTreeUtils.assembleTree(menuList);
    }

}
