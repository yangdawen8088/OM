package com.zero2oneit.mall.log.service.impl;

import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.query.log.LogDetailQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.log.mapper.LogDetailMapper;
import com.zero2oneit.mall.log.service.LogDetailService;

import java.util.Collections;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
@Service
public class LogDetailServiceImpl extends ServiceImpl<LogDetailMapper, LogDetail> implements LogDetailService {

    @Autowired
    private LogDetailMapper logDetailMapper;

    @Override
    public BoostrapDataGrid pageList(LogDetailQueryObject qo) {
        //查询总记录数
        int total = logDetailMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : logDetailMapper.selectRows(qo));
    }

}