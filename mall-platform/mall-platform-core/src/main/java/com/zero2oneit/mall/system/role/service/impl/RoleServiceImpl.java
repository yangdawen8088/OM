package com.zero2oneit.mall.system.role.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.role.entity.Role;
import com.zero2oneit.mall.system.role.mapper.RoleMapper;
import com.zero2oneit.mall.system.role.query.RoleQueryObject;
import com.zero2oneit.mall.system.role.service.RoleService;
import com.zero2oneit.mall.system.role.vo.RoleVO;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BoostrapDataGrid queryList(RoleQueryObject qo) {
        //查询总记录数
        int total = roleMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : roleMapper.selectRows(qo));
    }

    @Override
    public void add(RoleVO roleVO) {
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        roleMapper.add(new Role(roleVO.getRoleName(), roleVO.getRoleCode(),
                currentUser.getId(), new Date(), roleVO.getNote()));
    }

    @Override
    public void edit(RoleVO roleVO) {
        roleMapper.edit(roleVO);
    }

    @Override
    public void delRoleByIds(String ids) {
        roleMapper.delRoleByIds(ids);
    }

    @Override
    public List<?> getAllRole() {
        return roleMapper.getAllRole();
    }

    @Override
    public Set<String> selectUserRoleByUserId(Long userId) {
        return roleMapper.selectUserRoleByUserId(userId);
    }

}
