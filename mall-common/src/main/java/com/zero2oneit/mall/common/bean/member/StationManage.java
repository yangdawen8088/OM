package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 10:55
 */
@Data
@TableName("station_manage")
public class StationManage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 图片
     */
    private String picture;

    /**
     * 区域id
     */
    private String areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 驿站状态：0-关闭，1-开启
     */
    private String stationStatus;

    /**
     * 审核 0-否 1-是
     */
    private Integer grantType;
}
