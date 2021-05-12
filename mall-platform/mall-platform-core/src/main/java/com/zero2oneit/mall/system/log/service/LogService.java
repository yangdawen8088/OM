package com.zero2oneit.mall.system.log.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.log.query.LogQueryObject;

import java.util.Date;

public interface LogService {

    void setLogoutTime(Date date, String id);

    BoostrapDataGrid queryList(LogQueryObject qo);

    BoostrapDataGrid queryOpList(LogQueryObject qo);

}
