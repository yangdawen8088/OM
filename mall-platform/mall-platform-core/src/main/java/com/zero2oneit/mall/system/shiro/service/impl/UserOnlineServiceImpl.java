package com.zero2oneit.mall.system.shiro.service.impl;

import com.zero2oneit.mall.common.utils.DateUtil;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.system.shiro.entity.SysUserOnline;
import com.zero2oneit.mall.system.shiro.mapper.ShiroMapper;
import com.zero2oneit.mall.system.shiro.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserOnlineServiceImpl implements UserOnlineService {

    @Autowired
    private ShiroMapper shiroMapper;

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        SysUserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline)) {
            shiroMapper.deleteOnlineById(sessionId);
        }
    }

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return shiroMapper.selectOnlineById(sessionId);
    }

    /**
     * 查询会话集合
     *
     * @param expiredDate 失效日期
     */
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return shiroMapper.selectOnlineByExpired(lastAccessTime);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionIds 会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteOnline(List<String> sessionIds) {
        for (String sessionId : sessionIds) {
            SysUserOnline userOnline = selectOnlineById(sessionId);
            if (StringUtils.isNotNull(userOnline)) {
                shiroMapper.deleteOnlineById(sessionId);
            }
        }
    }

}
