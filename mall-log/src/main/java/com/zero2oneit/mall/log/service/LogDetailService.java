package com.zero2oneit.mall.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.query.log.LogDetailQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
public interface LogDetailService extends IService<LogDetail> {

    BoostrapDataGrid pageList(LogDetailQueryObject qo);

}

