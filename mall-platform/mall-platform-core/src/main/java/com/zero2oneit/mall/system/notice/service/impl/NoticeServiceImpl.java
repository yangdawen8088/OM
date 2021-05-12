package com.zero2oneit.mall.system.notice.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.notice.mapper.NoticeMapper;
import com.zero2oneit.mall.system.notice.query.NoticeQueryObject;
import com.zero2oneit.mall.system.notice.service.NoticeService;
import com.zero2oneit.mall.system.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public BoostrapDataGrid queryList(NoticeQueryObject qo) {
        //查询总记录数
        int total = noticeMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : noticeMapper.selectRows(qo));
    }

    @Override
    public void addOrEditNotice(NoticeVO vo) {
        vo.setUserId(UserContext.getCurrentUser(UserContext.SYS_USER).getId());
        if (vo.getId() == null){//添加
            noticeMapper.add(vo);
        }else{//编辑
            noticeMapper.edit(vo);
        }
    }

    @Override
    public void changeNoticeStatus(String status, String ids) {
        noticeMapper.changeNoticeStatus(status, ids);
    }

    @Override
    public BoostrapDataGrid messages(QueryObject qo) {
        //查询总记录数
        int total = noticeMapper.selectTotalApi(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : noticeMapper.selectRowsApi(qo));
    }

}
