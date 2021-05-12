package com.zero2oneit.mall.system.base.init;

import com.zero2oneit.mall.system.power.entity.PowerBtn;
import com.zero2oneit.mall.system.power.mapper.PowerMapper;
import com.zero2oneit.mall.system.shiro.mapper.ShiroMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化所有用户按钮权限
 */
@Component
public class InitPemissions implements InitializingBean {

    public static Map<Long, List<String>> permissions = new HashMap<>();

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private ShiroMapper shiroMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化所有用户按钮权限
        for (PowerBtn powerBtn : powerMapper.loadBtnPermissions()) {
            permissions.put(powerBtn.getUserId(), Arrays.asList(powerBtn.getFunctionId().split(",")));
        }

        shiroMapper.deleteOnline();
        System.out.println(permissions);
    }

    public static Map<Long, List<String>> getPermissions() {
        return permissions;
    }

    public static void setPermissions(Map<Long, List<String>> permissions) {
        InitPemissions.permissions = permissions;
    }

}
