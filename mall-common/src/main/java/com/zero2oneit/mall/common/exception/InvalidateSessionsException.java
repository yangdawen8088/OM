package com.zero2oneit.mall.common.exception;

/**
 * Description: 无效会话异常
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class InvalidateSessionsException extends RuntimeException {

    public InvalidateSessionsException(String errorMsg){
        super(errorMsg);
    }

}
