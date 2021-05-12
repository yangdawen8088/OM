package com.zero2oneit.mall.system.menu.mapper;

import com.zero2oneit.mall.common.utils.tree.HomeMenu;
import com.zero2oneit.mall.system.menu.entity.Menu;
import com.zero2oneit.mall.system.menu.query.MenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MenuMapper {

    List<Menu> selectMenuList();

    void addOneLevelMenu(Menu menu);

    void addFunction(Menu menu);

    void delMenuByIds(@Param("ids") String ids, @Param("menuStatus") Integer menuStatus);

    void edit(Menu menu);

    int selectTotal(MenuQueryObject qo);

    List<HashMap<String,Object>> selectRows(MenuQueryObject qo);

    List<HashMap<String,Object>> loadHomeMenus(Long userId);

    List<HomeMenu> loadHomeTreeMenu(Long userId);

}
