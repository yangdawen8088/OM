package com.zero2oneit.mall.system.shiro.mapper;

import com.zero2oneit.mall.system.shiro.entity.SysUserOnline;

import java.util.List;
import java.util.Set;

public interface ShiroMapper {

    void saveOnline(SysUserOnline online);

    void deleteOnlineById(String sessionId);

    void deleteOnline();

    SysUserOnline selectOnlineById(String sessionId);

    List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);

    Set<String> selectOnlineSessionIds();

}
