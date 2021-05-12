package com.zero2oneit.mall.feign.log;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.log.query.LogQueryObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:
 *
 * @author Sinper
 * @date 2020/7/7 17:41
 */
@FeignClient("log-service")
public interface LogFeign {

    /**
     * 查询日志列表
     * @param qo
     * @return
     */
    @PostMapping("/admin/log/list")
    BoostrapDataGrid pageList(@RequestBody LogQueryObject qo);

}
