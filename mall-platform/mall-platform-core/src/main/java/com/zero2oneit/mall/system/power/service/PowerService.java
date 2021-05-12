package com.zero2oneit.mall.system.power.service;

import com.zero2oneit.mall.system.power.entity.DeptUser;
import com.zero2oneit.mall.system.power.vo.PowerVO;

import java.util.List;
import java.util.Set;

public interface PowerService {

    void saveUserOrRoleFunction(PowerVO vo);

    List<Integer> loadUserOrRoleFunction(PowerVO vo);

    List<DeptUser> deptUsersList();

    void saveUserOrRoleData(PowerVO vo);

    List<Integer> loadUserOrRoleData(PowerVO vo);

    Set<String> selectPermsByUserId(Long userId);

}
