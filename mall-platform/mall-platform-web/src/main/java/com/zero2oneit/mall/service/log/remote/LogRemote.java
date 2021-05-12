package com.zero2oneit.mall.service.log.remote;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.log.LogFeign;
import com.zero2oneit.mall.system.log.query.LogQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 异常日志服务
 *
 * @author Sinper
 * @date 2020/7/7 18:41
 */
@RestController
@RequestMapping("/remote/log")
public class LogRemote {

    @Autowired
    private LogFeign logFeign;

    /**
     * 远程调用-日志列表信息
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody LogQueryObject qo){
        return logFeign.pageList(qo);
    }

}
