package com.zero2oneit.mall.system.common.handle;

import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.enums.LogSucceed;
import com.zero2oneit.mall.common.enums.LogType;
import com.zero2oneit.mall.common.exception.CustomException;
import com.zero2oneit.mall.common.exception.InvalidateSessionsException;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private ObjectError error;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 处理自定义的异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R customExceptionHandler(CustomException e){
        return R.fail(e.getMessage());
    }

    /**
     * 处理权限自定义的异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public R authenticationExceptionHandler(AuthenticationException e){
        return R.fail(e.getMessage());
    }

    /**
     * 回话过期异常
     */
    @ExceptionHandler(InvalidateSessionsException.class)
    @ResponseBody
    public R invalidateSessionsExceptionExceptionHandler(InvalidateSessionsException e){
        return R.fail("gq");
    }

    /**
     * 处理bean validtation校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R exceptionHandler(BindException e){
        StringBuilder sb = new StringBuilder() ;
        List<ObjectError> errors = e.getAllErrors();
        for(ObjectError error : errors){
            sb.append(error.getDefaultMessage()).append(" ; ") ;
        }
        return R.fail(sb.toString());
    }

    /**
     * 异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest request, Exception e) {
        LogDetail log = new LogDetail(null, LogType.EXCEPTION.getMessage(), null, null, request.getRequestURI(), LogSucceed.FAIL.getMessage(), StringUtils.getExceptionMsg(e));
        amqpTemplate.convertAndSend("om.mall.exception.exchange", "exception.insert",  log);
        e.printStackTrace();
        return R.fail("服务器繁忙，稍后再请求...");
    }

}
