package com.zero2oneit.mall.system.notice.mapper;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.notice.query.NoticeQueryObject;
import com.zero2oneit.mall.system.notice.vo.NoticeVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface NoticeMapper {

    int selectTotal(NoticeQueryObject qo);

    List<HashMap<String,Object>> selectRows(NoticeQueryObject qo);

    void add(NoticeVO vo);

    void edit(NoticeVO vo);

    void changeNoticeStatus(@Param("status") String status, @Param("ids") String ids);

    int selectTotalApi(QueryObject qo);

    List<HashMap<String,Object>> selectRowsApi(QueryObject qo);

}
