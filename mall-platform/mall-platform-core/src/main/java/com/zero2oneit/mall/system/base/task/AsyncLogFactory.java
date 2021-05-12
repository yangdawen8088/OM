package com.zero2oneit.mall.system.base.task;

import com.zero2oneit.mall.common.enums.LogSucceed;
import com.zero2oneit.mall.common.enums.LogType;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.common.utils.spring.SpringUtils;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.log.entity.FlatformLoginLog;
import com.zero2oneit.mall.system.log.entity.OperationLog;
import com.zero2oneit.mall.system.log.mapper.LogMapper;
import com.zero2oneit.mall.system.shiro.entity.OnlineSession;
import com.zero2oneit.mall.system.shiro.entity.SysUserOnline;
import com.zero2oneit.mall.system.shiro.mapper.ShiroMapper;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步日志工厂类（产生任务用）
 */
public class AsyncLogFactory {

    private static final Logger logger = LoggerFactory.getLogger(AsyncLogFactory.class);

    private static LogMapper logMapper = SpringUtils.getBean(LogMapper.class);
    private static ShiroMapper shiroMapper = SpringUtils.getBean(ShiroMapper.class);

    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation("无");
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                shiroMapper.saveOnline(online);
            }
        };
    }

    /**
     * 操作日志记录
     */
    public static TimerTask operateLog(final Long userId, final String serviceName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogFactory.createOperationLog(
                        LogType.BUSSINESS, userId, serviceName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                    logMapper.addOperationLog(operationLog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 记录未知异常日志
     */
    public static TimerTask exceptionLog(final Long userId, final String methodName, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = StringUtils.getExceptionMsg(exception);
                OperationLog operationLog = LogFactory.createOperationLog(
                        LogType.EXCEPTION, userId, "", null, methodName, msg, LogSucceed.FAIL);
                try {
                    logMapper.addOperationLog(operationLog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 记录登陆信息
     * @param userId 用户ID
     * @param sessionId 回话ID
     * @param ip  登录IP
     * @return 任务task
     */
    public static TimerTask loginLog(final Long userId, final String sessionId, final String ip) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(UserContext.getRequest().getHeader("User-Agent"));
        return new TimerTask() {
            @Override
            public void run() {
                String address = "无";
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                FlatformLoginLog loginLog = LogFactory.createFlatformLoginLog(userId, sessionId, ip, address, os, browser);
                // 插入数据
                try {
                    logMapper.addFlatformLoginLog(loginLog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
