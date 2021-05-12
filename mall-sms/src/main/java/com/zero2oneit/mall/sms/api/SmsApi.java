package com.zero2oneit.mall.sms.api;

import com.zero2oneit.mall.sms.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 阿里云发送短信
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/19
 */
@RestController
@RequestMapping("/api/sms/msg")
public class SmsApi {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 账号、密码注册
     * @param
     * @return
     */
    @PostMapping("/send")
    public R send(@RequestBody Map<String, String> params){
        if(params.get("phone").length() == 0){
            return R.fail("手机号码不能为空");
        }
        //获取6位随机验证码
        int random = (int)((Math.random()*9+1)*100000);
        redisTemplate.opsForValue().set("om:sms:verify:code:" + params.get("phone"), String.valueOf(random), 600, TimeUnit.SECONDS);
        return R.ok("发送成功");
    }

    /**
     * 修改支付发送验证码
     * @param
     * @return
     */
    @PostMapping("/sendPwd")
    public R sendPwd(@RequestBody Map<String, String> params){
        if(params.get("phone").length() == 0){
            return R.fail("手机号码不能为空");
        }
        //获取6位随机验证码
        int random = (int)((Math.random()*9+1)*100000);
        redisTemplate.opsForValue().set("om:sms:pwd:code:" + params.get("phone"), String.valueOf(random), 600, TimeUnit.SECONDS);
        return R.ok("发送成功");
    }

}
