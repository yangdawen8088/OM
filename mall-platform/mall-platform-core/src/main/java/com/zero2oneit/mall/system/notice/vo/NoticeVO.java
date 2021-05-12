package com.zero2oneit.mall.system.notice.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NoticeVO {

    private Long id;

    private Long userId;

    private String noticeTitle;

    private String noticeContent;

    private Integer noticeType;

    private Integer noticeLevel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;

}
