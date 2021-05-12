/*
该类已不使用
package com.zero2oneit.mall.common.exception;

import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.enums.LogSucceed;
import com.zero2oneit.mall.common.enums.LogType;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * Description:全局异常处理器
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 *//*

@ControllerAdvice
@Component
public class GlobalException {

    @Autowired
    private AmqpTemplate amqpTemplate;

    */
/**
     * 处理自定义的异常
     *//*

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R customExceptionHandler(CustomException e){
        return R.fail(e.getMessage());
    }

    */
/**
     * 异常统一处理
     *//*

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest request, Exception e) {
        LogDetail log = new LogDetail(null, LogType.EXCEPTION.getMessage(), null, null, request.getRequestURI(), LogSucceed.FAIL.getMessage(), StringUtils.getExceptionMsg(e));
        System.out.println("============================");
        amqpTemplate.convertAndSend("om.mall.exception.exchange", "exception.insert",  log);
        e.printStackTrace();
        return R.fail("服务器繁忙，稍后再请求...");
    }

}
*/
