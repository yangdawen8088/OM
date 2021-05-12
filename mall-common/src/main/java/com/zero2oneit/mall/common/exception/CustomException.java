package com.zero2oneit.mall.common.exception;

/**
 * Description:自定义异常
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class CustomException extends RuntimeException{

    public CustomException(String errorMessage){
        super(errorMessage);
    }

}
