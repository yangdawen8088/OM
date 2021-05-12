package com.zero2oneit.mall.search.exception;

import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.utils.R;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Description:全局异常处理器
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
@ControllerAdvice
public class GlobalException {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest request, Exception e) {
        LogDetail log = new LogDetail(null, "未知异常日志", null, null, request.getRequestURI(), "失败", getExceptionMsg(e));
        amqpTemplate.convertAndSend("om.mall.exception.exchange", "exception.insert",  log);
        e.printStackTrace();
        return R.fail("开了个小差,很快就好了...");
    }

    /**
     * 获取异常的具体信息
     */
    public String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }

}
