package com.zero2oneit.mall.system.quartz.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.quartz.entity.SysJob;
import com.zero2oneit.mall.system.quartz.query.QuartzQueryObject;
import com.zero2oneit.mall.system.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 任务管理
 */
@Controller
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/quartzPage")
    @ResponseBody
    public BoostrapDataGrid quartzPage(@RequestBody QuartzQueryObject qo) {//VO : value-object 值对象
        return quartzService.queryList(qo);
    }

    @RequestMapping("/quartzLogPage")
    @ResponseBody
    public BoostrapDataGrid quartzLogPage(@RequestBody QuartzQueryObject qo) {//VO : value-object 值对象
        return quartzService.quartzLogPage(qo);
    }


    //添加或编辑角色信息
    @RequestMapping("/addOrEditQuartz")
    @ResponseBody
    public R addOrEditQuartz(@Validated SysJob job) {//VO : value-object 值对象
        quartzService.addOrEditQuartz(job);
        return R.ok("操作成功");
    }

    @RequestMapping("/changeQuartzStatus")
    @ResponseBody
    public R changeQuartzStatus(SysJob job) {//VO : value-object 值对象
        quartzService.changeQuartzStatus(job);
        return R.ok("改变用户状态成功");
    }

    @RequestMapping("/delQuartz")
    @ResponseBody
    public R delQuartz(SysJob job) {//VO : value-object 值对象
        quartzService.delQuartz(job);
        return R.ok("删除任务成功");
    }

}
