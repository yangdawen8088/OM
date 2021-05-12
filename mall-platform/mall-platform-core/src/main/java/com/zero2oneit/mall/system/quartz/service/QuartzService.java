package com.zero2oneit.mall.system.quartz.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.quartz.entity.SysJob;
import com.zero2oneit.mall.system.quartz.query.QuartzQueryObject;

public interface QuartzService {

    BoostrapDataGrid queryList(QuartzQueryObject qo);

    void addOrEditQuartz(SysJob job);

    void changeQuartzStatus(SysJob job);

    void delQuartz(SysJob job);

    BoostrapDataGrid quartzLogPage(QuartzQueryObject qo);

}
