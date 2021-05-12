package com.zero2oneit.mall.system.shiro.service;

import com.zero2oneit.mall.system.shiro.entity.SysUserOnline;

import java.util.Date;
import java.util.List;

public interface UserOnlineService {

    void deleteOnlineById(String sessionId);

    SysUserOnline selectOnlineById(String sessionId);

    List<SysUserOnline> selectOnlineByExpired(Date expiredDate);

    void batchDeleteOnline(List<String> sessionIds);

}
