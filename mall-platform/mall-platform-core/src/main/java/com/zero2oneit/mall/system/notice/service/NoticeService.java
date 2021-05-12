package com.zero2oneit.mall.system.notice.service;


import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.notice.query.NoticeQueryObject;
import com.zero2oneit.mall.system.notice.vo.NoticeVO;

public interface NoticeService {

    BoostrapDataGrid queryList(NoticeQueryObject qo);

    void addOrEditNotice(NoticeVO vo);

    void changeNoticeStatus(String status, String ids);

    BoostrapDataGrid messages(QueryObject qo);

}
