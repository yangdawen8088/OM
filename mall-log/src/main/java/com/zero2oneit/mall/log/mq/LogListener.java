package com.zero2oneit.mall.log.mq;

import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.log.service.LogDetailService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Sinper
 * @date 2020/7/7 12:15
 */
@Component
public class LogListener {

    @Autowired
    private LogDetailService detailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "om.mall.exception.queue", durable = "true"),
            exchange = @Exchange(
                    value = "om.mall.exception.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"exception.insert"}))
    public void listenExceptionMsg(LogDetail log) {
        System.out.println("----------------------");
        if (log != null) {
           detailService.save(log);
        }
    }

}
