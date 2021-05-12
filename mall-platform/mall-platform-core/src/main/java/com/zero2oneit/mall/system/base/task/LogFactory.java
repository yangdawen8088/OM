package com.zero2oneit.mall.system.base.task;

import com.zero2oneit.mall.common.enums.LogSucceed;
import com.zero2oneit.mall.common.enums.LogType;
import com.zero2oneit.mall.system.log.entity.FlatformLoginLog;
import com.zero2oneit.mall.system.log.entity.OperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 */
public class LogFactory {

    /**
     * 创建操作日志与未知异常
     */
    public static OperationLog createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserId(userId);
        operationLog.setClassName(clazzName);
        operationLog.setMethodName(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建平台登录用户日志
     */
    public static FlatformLoginLog createFlatformLoginLog(Long userId, String sessionId, String loginIp, String ipAddress, String os, String browser) {
        FlatformLoginLog loginLog = new FlatformLoginLog();
        loginLog.setLoginTime(new Date());
        loginLog.setUserId(userId);
        loginLog.setSessionId(sessionId);
        loginLog.setLoginIp(loginIp);
        loginLog.setIpAddress(ipAddress);
        loginLog.setOs(os);
        loginLog.setBrowser(browser);
        loginLog.setLoginType(1);
        return loginLog;
    }

}
