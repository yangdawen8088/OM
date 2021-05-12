package com.zero2oneit.mall.system.power.service.impl;

import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.system.base.init.InitPemissions;
import com.zero2oneit.mall.system.base.utils.SessionBean;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.power.entity.DeptUser;
import com.zero2oneit.mall.system.power.entity.PowerBtn;
import com.zero2oneit.mall.system.power.mapper.PowerMapper;
import com.zero2oneit.mall.system.power.service.PowerService;
import com.zero2oneit.mall.system.power.vo.PowerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;

    @Override
    public void saveUserOrRoleFunction(PowerVO vo) {
        if(vo.getType() == 1){
            powerMapper.delUserFunction(vo);
            powerMapper.saveUserFunction(vo);
        }else{
            powerMapper.delRoleFunction(vo);
            powerMapper.saveRoleFunction(vo);
        }

        //初始化所有用户按钮权限
        InitPemissions.permissions.clear();
        for (PowerBtn powerBtn : powerMapper.loadBtnPermissions()) {
            InitPemissions.permissions.put(powerBtn.getUserId(), Arrays.asList(powerBtn.getFunctionId().split(",")));
            Map<Long, HttpSession> userSession = SessionBean.getUserSession();
            HttpSession httpSession = userSession.get(powerBtn.getUserId());
            if(httpSession != null && httpSession.getAttribute(UserContext.SYS_USER) != null){
               httpSession.setAttribute("btnPermissions", Arrays.asList(powerBtn.getFunctionId().split(",")));
               System.out.println(powerBtn.getUserId() + "----------" + Arrays.asList(powerBtn.getFunctionId().split(",")));
            }
        }

    }

    @Override
    public List<Integer> loadUserOrRoleFunction(PowerVO vo) {
        List<Integer> list = null;
        if(vo.getType() == 1){
            list = powerMapper.loadUserFunction(vo);
        }else{
            list = powerMapper.loadRoleFunction(vo);
        }
        return list;
    }

    @Override
    public List<DeptUser> deptUsersList() {
        List<DeptUser> menuList = powerMapper.deptUsersList();
        //将原数据转换成树形结构的数据格式
        return TreeUtils.assembleTree(menuList);
    }

    @Override
    public void saveUserOrRoleData(PowerVO vo) {
        if(vo.getType() == 1){
            powerMapper.delUserData(vo);
            powerMapper.saveUserData(vo);
        }else{
            powerMapper.delRoleData(vo);
            powerMapper.saveRoleData(vo);
        }
    }

    @Override
    public List<Integer> loadUserOrRoleData(PowerVO vo) {
        List<Integer> list = null;
        if(vo.getType() == 1){
            list = powerMapper.loadUserData(vo);
        }else{
            list = powerMapper.loadRoleData(vo);
        }
        return list;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return powerMapper.selectPermsByUserId(userId);
    }

}
