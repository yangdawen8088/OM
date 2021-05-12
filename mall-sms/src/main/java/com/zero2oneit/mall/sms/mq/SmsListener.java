package com.zero2oneit.mall.sms.mq;

import com.zero2oneit.mall.sms.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: mq短信监听
 *
 * @author Sinper
 * @date 2020/7/2 11:16
 */
@Component
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;


}
