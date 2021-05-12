package com.zero2oneit.mall.log.controller;

import com.zero2oneit.mall.common.query.log.LogDetailQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zero2oneit.mall.log.service.LogDetailService;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/admin/log")
public class LogDetailController {

    @Autowired
    private LogDetailService logDetailService;

    /**
     * 查询日志列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody LogDetailQueryObject qo){
        return logDetailService.pageList(qo);
    }

}
