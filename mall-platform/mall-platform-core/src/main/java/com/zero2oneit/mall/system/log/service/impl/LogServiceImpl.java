package com.zero2oneit.mall.system.log.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.log.mapper.LogMapper;
import com.zero2oneit.mall.system.log.query.LogQueryObject;
import com.zero2oneit.mall.system.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper loginLogMapper;

    @Override
    public void setLogoutTime(Date date, String id) {
        loginLogMapper.setLogoutTime(date, id);
    }

    @Override
    public BoostrapDataGrid queryList(LogQueryObject qo) {
        //查询总记录数
        int total = loginLogMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : loginLogMapper.selectRows(qo));
    }

    @Override
    public BoostrapDataGrid queryOpList(LogQueryObject qo) {
        //查询总记录数
        int total = loginLogMapper.selectOpTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : loginLogMapper.selectOpRows(qo));
    }

}
