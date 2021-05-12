package com.zero2oneit.mall.system.server.mapper;

import com.zero2oneit.mall.system.server.entity.SysServer;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/21 17:24
 */
public interface ServerMapper {

    List<SysServer> selectServerList();

    void add(SysServer serverVO);

    void edit(SysServer serverVO);

}
