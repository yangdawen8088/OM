package com.zero2oneit.mall.common.exception;

/**
 * Description: 计划策略异常
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class TaskException extends Exception {

    private Code code;

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx) {
        super(msg, nestedEx);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public enum Code {
        TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE
    }

}