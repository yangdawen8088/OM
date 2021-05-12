package com.zero2oneit.mall.common.query.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero2oneit.mall.common.utils.DateUtil;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
@Data
public class LogDetailQueryObject extends QueryObject {

    private String logType;

    private String logName;

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
