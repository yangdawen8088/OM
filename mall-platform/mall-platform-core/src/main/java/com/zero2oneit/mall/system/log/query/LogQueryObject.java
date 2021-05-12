package com.zero2oneit.mall.system.log.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero2oneit.mall.common.utils.DateUtil;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

import java.util.Date;

@Data
public class LogQueryObject extends QueryObject {

    private String account;
    private String logType;

    @JsonFormat(pattern = "yyyy-MM-dd")  //接收字符串时间处理
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public void setStartTime(Date startTime) {
        this.startTime = DateUtil.getBeginDate(startTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = DateUtil.getEndDate(endTime);
    }

}
