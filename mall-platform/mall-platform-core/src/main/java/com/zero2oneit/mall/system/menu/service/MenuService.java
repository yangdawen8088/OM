package com.zero2oneit.mall.system.menu.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.HomeMenu;
import com.zero2oneit.mall.system.menu.entity.Menu;
import com.zero2oneit.mall.system.menu.query.MenuQueryObject;

import java.util.HashMap;
import java.util.List;

public interface MenuService {

    List<Menu> menuList();

    void add(Menu menu);

    void edit(Menu menu);

    void delMenuByIds(String ids, Integer menuStatus);

    BoostrapDataGrid queryList(MenuQueryObject qo);

    List<HashMap<String,Object>> loadHomeMenus();

    List<HomeMenu> loadHomeTreeMenus();

}
