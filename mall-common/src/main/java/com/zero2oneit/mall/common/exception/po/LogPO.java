package com.zero2oneit.mall.common.exception.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 业务日志对象
 *
 * @author Sinper
 * @date 2020/7/7 10:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    private String logType;

    /**
     * 业务名称
     */
    private String logName;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 业务操作状态：成功 失败
     */
    private String logStaus;

    /**
     * 记录日志信息（错误或内容）
     */
    private String errorMsg;

}
