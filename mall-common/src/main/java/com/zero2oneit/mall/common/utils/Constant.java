package com.zero2oneit.mall.common.utils;

import java.math.BigDecimal;

/**
 * Description: 常量
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class Constant {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(19[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$";

    /**
     * 零
     */
    public static final BigDecimal ZERO = new BigDecimal("0.00");

    /**
     * 保存精度
     */
    public static final int SCALE_STORE = 4;

    /**
     * 显示精度
     */
    public static final int SCALE_SHOW = 2;

    /**
     * 计算精度
     */
    public static final int SCALE_CAL = SCALE_STORE * 2;

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

}
