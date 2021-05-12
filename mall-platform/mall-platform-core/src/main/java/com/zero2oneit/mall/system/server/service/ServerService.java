package com.zero2oneit.mall.system.server.service;

import com.zero2oneit.mall.system.server.entity.SysServer;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/21 17:29
 */
public interface ServerService {

    List<SysServer> serverList();

    void add(SysServer serverVO);

    void edit(SysServer serverVO);

}
