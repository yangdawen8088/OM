package com.zero2oneit.mall.common.bean.order;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author leon
 * @date 2021/3/17 11:20
 */
@Data
public class OrderAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    private String receiverName;
    private String provinceName;
    private String cityName;
    private String regionName;
    private String streetAddr;
    private String receivePhone;
}
