package com.zero2oneit.mall.system.server.service.impl;

import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.system.server.entity.SysServer;
import com.zero2oneit.mall.system.server.mapper.ServerMapper;
import com.zero2oneit.mall.system.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/21 17:30
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerMapper serverMapper;

    @Override
    public List<SysServer> serverList() {
        //将原数据转换成树形结构的数据格式
        return TreeUtils.assembleTree(serverMapper.selectServerList());
    }

    @Override
    public void add(SysServer serverVO) {
        serverMapper.add(serverVO);
    }

    @Override
    public void edit(SysServer serverVO) {
        serverMapper.edit(serverVO);
    }

}
