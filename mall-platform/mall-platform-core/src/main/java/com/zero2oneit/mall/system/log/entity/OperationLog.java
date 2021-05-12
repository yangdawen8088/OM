package com.zero2oneit.mall.system.log.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 日志类型
     */
    private String logtype;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否成功
     */
    private String succeed;
    /**
     * 备注
     */
    private String message;

}
