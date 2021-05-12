package com.zero2oneit.mall.system.role.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.role.query.RoleQueryObject;
import com.zero2oneit.mall.system.role.vo.RoleVO;

import java.util.List;
import java.util.Set;

public interface RoleService {

    BoostrapDataGrid queryList(RoleQueryObject qo);

    void add(RoleVO roleVO);

    void edit(RoleVO roleVO);

    void delRoleByIds(String ids);

    List<?> getAllRole();

    Set<String> selectUserRoleByUserId(Long userId);

}
