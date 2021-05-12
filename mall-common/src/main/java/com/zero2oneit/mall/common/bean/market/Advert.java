package com.zero2oneit.mall.common.bean.market;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero2oneit.mall.common.utils.DateUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-01-25
 */
@Data
@TableName("m_advert")
public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @TableId
    private Long id;
    /**
     * 广告名称
     */
    private String advertName;
    /**
     * 广告位置：1-首页轮播
     */
    private Integer advertPosition;
    /**
     * 广告图片路径
     */
    private String picUrl;
    /**
     * 广告开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //接收字符串时间处理
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 广告结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endTime;

    /**
     * 广告状态：默认发布：1-发布 2-关闭
     */
    private Integer advertStatus;
    /**
     * 是否开启时间限制：0-默认不开启 1-开启
     */
    private Integer isOpen;
    /**
     * 区域名称
     */
    private String city;
    /**
     * 广告跳转链接
     */
    private String advertUrl;
    /**
     * 广告排序
     */
    private Integer advertOrder;
    /**
     * 操作人id
     */
    private Long operatorId;
    /**
     * 操作时间
     */
    private Date operatorTime;

}
