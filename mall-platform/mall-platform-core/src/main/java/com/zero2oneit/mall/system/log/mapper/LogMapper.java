package com.zero2oneit.mall.system.log.mapper;

import com.zero2oneit.mall.system.log.entity.FlatformLoginLog;
import com.zero2oneit.mall.system.log.entity.OperationLog;
import com.zero2oneit.mall.system.log.query.LogQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface LogMapper {

    void addOperationLog(OperationLog operationLog);

    void addFlatformLoginLog(FlatformLoginLog loginLog);

    void setLogoutTime(@Param("date") Date date, @Param("sessionId") String sessionId);

    int selectTotal(LogQueryObject qo);

    List<HashMap<String,Object>> selectRows(LogQueryObject qo);

    int selectOpTotal(LogQueryObject qo);

    List<HashMap<String,Object>> selectOpRows(LogQueryObject qo);

}
