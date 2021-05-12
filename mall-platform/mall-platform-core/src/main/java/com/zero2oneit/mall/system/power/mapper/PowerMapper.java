package com.zero2oneit.mall.system.power.mapper;

import com.zero2oneit.mall.system.power.entity.DeptUser;
import com.zero2oneit.mall.system.power.entity.PowerBtn;
import com.zero2oneit.mall.system.power.vo.PowerVO;

import java.util.List;
import java.util.Set;

public interface PowerMapper {

    void saveUserFunction(PowerVO vo);

    void saveRoleFunction(PowerVO vo);

    void delUserFunction(PowerVO vo);

    void delRoleFunction(PowerVO vo);

    List<Integer> loadUserFunction(PowerVO vo);

    List<Integer> loadRoleFunction(PowerVO vo);

    List<PowerBtn> loadBtnPermissions();

    List<DeptUser> deptUsersList();

    void delUserData(PowerVO vo);

    void delRoleData(PowerVO vo);

    void saveUserData(PowerVO vo);

    void saveRoleData(PowerVO vo);

    List<Integer> loadUserData(PowerVO vo);

    List<Integer> loadRoleData(PowerVO vo);

    Set<String> selectPermsByUserId(Long userId);

}
