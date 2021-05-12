package com.zero2oneit.mall.system.role.mapper;

import com.zero2oneit.mall.system.role.entity.Role;
import com.zero2oneit.mall.system.role.query.RoleQueryObject;
import com.zero2oneit.mall.system.role.vo.RoleVO;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface RoleMapper {

    int selectTotal(RoleQueryObject qo);

    List<HashMap<String,String>> selectRows(RoleQueryObject qo);

    void add(Role role);

    void edit(RoleVO roleVO);

    void delRoleByIds(String ids);

    List<HashMap<String,Object>> getAllRole();

    Set<String> selectUserRoleByUserId(Long userId);

}
