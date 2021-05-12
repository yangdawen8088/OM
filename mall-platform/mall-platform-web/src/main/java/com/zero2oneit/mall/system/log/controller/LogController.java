package com.zero2oneit.mall.system.log.controller;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.log.query.LogQueryObject;
import com.zero2oneit.mall.system.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/loginLogPage")
    @ResponseBody
    public BoostrapDataGrid loginLogPage(@RequestBody LogQueryObject qo) {//VO : value-object 值对象
        return logService.queryList(qo);
    }

    @RequestMapping("/opLogPage")
    @ResponseBody
    public BoostrapDataGrid opLogPage(@RequestBody LogQueryObject qo) {//VO : value-object 值对象
        return logService.queryOpList(qo);
    }

}
