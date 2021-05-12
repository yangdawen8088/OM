package com.zero2oneit.mall.system.quartz.mapper;


import com.zero2oneit.mall.system.quartz.entity.SysJob;
import com.zero2oneit.mall.system.quartz.entity.SysJobLog;
import com.zero2oneit.mall.system.quartz.query.QuartzQueryObject;

import java.util.HashMap;
import java.util.List;

public interface QuartzMapper {

    int selectTotal(QuartzQueryObject qo);

    List<HashMap<String,Object>> selectRows(QuartzQueryObject qo);

    int selectLogTotal(QuartzQueryObject qo);

    List<HashMap<String,Object>> selectLogRows(QuartzQueryObject qo);

    int add(SysJob job);

    int addJobLog(SysJobLog job);

    SysJob selectJobById(Long jobId);

    int edit(SysJob job);

    List<SysJob> selectJobAll();

    int deleteJobById(Long jobId);

}
