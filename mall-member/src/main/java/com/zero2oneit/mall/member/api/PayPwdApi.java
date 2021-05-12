package com.zero2oneit.mall.member.api;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 支付密码管理
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/19
 */
@RestController
@RequestMapping("/api/auth/member/pwd")
public class PayPwdApi {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置支付密码
     * @param
     * @return
     */
    @PostMapping("/setPayPwd")
    public R setPayPwd(@RequestBody Map<String, String> params){
        String vCode = redisTemplate.opsForValue().get("om:sms:pwd:code:" + params.get("memberPhone"));
        //验证码校验
        if(params.get("code").equals(vCode)){
            //判断账号是否存在
            if("2".equals(params.get("type"))){
                boolean isExist = memberInfoService.checkAccount(params.get("memberAccount"));
                if(!isExist){
                    memberInfoService.setPayPwd(params);
                    return R.ok("设置支付密码成功");
                }
                return R.fail("该手机号码不存在");
            }
            return R.ok("验证通过");
        }
        return R.fail("验证输入不正确");
    }

    /**
     * 修改支付密码
     * @param
     * @return
     */
    @PostMapping("/editPayPwd")
    public R editPayPwd(@RequestBody Map<String, String> params){
        //判断账号是否存在
        boolean isExist = memberInfoService.check(params.get("memberAccount"), params.get("payPwd"));
        if(isExist){
            memberInfoService.editPayPwd(params);
            return R.ok("修改支付密码成功");
        }
        return R.fail("原密码输入不正确");
    }

}
