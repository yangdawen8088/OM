package com.zero2oneit.mall.auth.controller;



import com.zero2oneit.mall.auth.feign.member.MemberLoginFeign;
import com.zero2oneit.mall.common.annotion.RepeatSubmit;
import com.zero2oneit.mall.common.bean.auth.AppWxLoginObject;
import com.zero2oneit.mall.common.bean.auth.RegisterInfo;
import com.zero2oneit.mall.common.utils.NumberUtil;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.RegexUtils;
import com.zero2oneit.mall.common.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     app登录
 * 会员基本信息表 前端控制器
 * </p>
 *
 * @author Lee
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/app/wxLogin")
public class apiWxLoginController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MemberLoginFeign loginFeign;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //app 微信授权登录
    @ApiOperation("app微信授权登录")
    @RequestMapping("/auth")
    public R appWxLogin(@RequestBody AppWxLoginObject appWxLoginObject){
        appWxLoginObject.setType("app");
        return  loginFeign.wxauth(appWxLoginObject);
    }

    @ApiOperation("app短信验证码登录")
    @RequestMapping("/appCodePhone")
    @RepeatSubmit
    public R appCodePhone(@RequestBody AppWxLoginObject appWxLoginObject){
        appWxLoginObject.setType("app");
        String s = redisTemplate.opsForValue().get("member:login:code:" + appWxLoginObject.getPhone());
        if(StringUtils.isEmpty(s)){
            return  R.fail("请点击发送验证码");
        }else{
            if(!s.equals(appWxLoginObject.getCode())){
                return R.fail("请输入正确的验证码");
            }
        }
        return  loginFeign.appCodePhone(appWxLoginObject);
    }

    @ApiOperation("app账号密码登录")
    @RequestMapping("/appPassPhone")
    @RepeatSubmit
    public R appPassPhone(@RequestBody AppWxLoginObject appWxLoginObject){
        appWxLoginObject.setType("app");
        return  loginFeign.appPassPhone(appWxLoginObject);
    }

    @ApiOperation("app微信绑定手机号码")
    @RequestMapping("/bindWx")
    @RepeatSubmit
    public R bindWx(@RequestBody AppWxLoginObject appWxLoginObject){
        String s = redisTemplate.opsForValue().get("member:login:code:" + appWxLoginObject.getPhone());
        if(StringUtils.isEmpty(s)){
            return  R.fail("请点击发送验证码");
        }else{
            if(!s.equals(appWxLoginObject.getCode())){
                return R.fail("请输入正确的验证码");
            }
        }
        appWxLoginObject.setType("app");
        return  loginFeign.bindWx(appWxLoginObject);
    }


    @ApiOperation("app注册下一步")
    @RequestMapping("/checkRegisterCode")
    public R checkRegisterCode(@RequestBody AppWxLoginObject appWxLoginObject){
        String s = redisTemplate.opsForValue().get("member:login:code:" + appWxLoginObject.getPhone());
        if(StringUtils.isEmpty(s)){
            return  R.fail("请点击发送验证码");
        }else{
            if(!s.equals(appWxLoginObject.getCode())){
                return R.fail("请输入正确的验证码");
            }
        }
        redisTemplate.opsForValue().set("register:key:"+appWxLoginObject.getPhone(),"setPassword",5, TimeUnit.MINUTES);
        return R.ok("验证码正确");

    }

    @ApiOperation("注册完成")
    @RequestMapping("/register")
    @RepeatSubmit
    public R register(@RequestBody AppWxLoginObject appWxLoginObject){
        String s = redisTemplate.opsForValue().get("register:key:"+appWxLoginObject.getPhone());
        appWxLoginObject.setType("app");
        if(StringUtils.isEmpty(s)){
            return  R.fail("验证已过期");
        }else{
            if("setPassword".equals(s)){
                return  loginFeign.register(appWxLoginObject);
            }
        }

     return R.fail("验证失败");

    }

    @ApiOperation("验证码发送")
    @RequestMapping("/appGetCode")
    public R appGetCode(String phone){

        Map<String,String> map =new HashMap<>();
        String code= NumberUtil.generateCode(6);
        String key="member:login:code:"+phone;
        map.put("phone",phone);
        map.put("content",code);
        map.put("key",key);
        amqpTemplate.convertAndSend("mall.sms.exchange", "sms.verify.code",map);
        redisTemplate.opsForValue().set(key,code,90,TimeUnit.SECONDS);
        return  R.ok("发送成功");
    }

    @ApiOperation("注册验证码发送")
    @RequestMapping("/appRegisterGetCode")
    public R appRegisterGetCode(String phone){
        return  loginFeign.checkPhone(phone) ? R.fail("此号码已注册") :appGetCode(phone);

    }

    @ApiOperation("忘记密码发送验证码")
    @RequestMapping("/appUpdateGetCode")
    public R appUpdateGetCode(String phone){
        return  loginFeign.checkPhone(phone) ? appUpdateGetCode2(phone) : R.fail("此用户不存在");

    }

    private R appUpdateGetCode2(String phone){
        Map<String,String> map =new HashMap<>();
        String code= NumberUtil.generateCode(6);
        String key="member:updatePass:code:"+phone;
        map.put("phone",phone);
        map.put("content",code);
        map.put("key",key);
        amqpTemplate.convertAndSend("mall.sms.exchange", "sms.verify.code",map);
        redisTemplate.opsForValue().set(key,code,90,TimeUnit.SECONDS);
        return  R.ok("发送成功");
    }

    @ApiOperation("app修改密码下一步")
    @RequestMapping("/checkUpdateCode")
    public R checkUpdateCode(@RequestBody AppWxLoginObject appWxLoginObject){
        String s = redisTemplate.opsForValue().get("member:updatePass:code:" + appWxLoginObject.getPhone());
        if(StringUtils.isEmpty(s)){
            return  R.fail("请点击发送验证码");
        }else{
            if(!s.equals(appWxLoginObject.getCode())){
                return R.fail("请输入正确的验证码");
            }
        }
        redisTemplate.opsForValue().set("updatePass:key:"+appWxLoginObject.getPhone(),"updatePass",5, TimeUnit.MINUTES);
        return R.ok("验证码正确");

    }



    @ApiOperation("修改密码")
    @RequestMapping("/appUpdatePass")
    @RepeatSubmit
    public R appUpdatePass(@RequestBody AppWxLoginObject appWxLoginObject){
        String s = redisTemplate.opsForValue().get("updatePass:key:"+appWxLoginObject.getPhone());
        appWxLoginObject.setType("app");
        if(StringUtils.isEmpty(s)){
            return  R.fail("验证已过期");
        }else{
            if("updatePass".equals(s)){
                return  loginFeign.appUpdatePass(appWxLoginObject);
            }
        }
        return R.fail("验证失败");
    }
    @ApiOperation("网页注册")
    @RequestMapping("/registerHtml")
    @RepeatSubmit
    public R registerHtml(RegisterInfo registerInfo){
        String s = redisTemplate.opsForValue().get("member:login:code:" + registerInfo.getPhone());
        if(!RegexUtils.checkPhone(registerInfo.getPhone())){
            return R.fail("请输入正确的手机号码");
        }
        if(StringUtils.isEmpty(s)){
            return  R.fail("验证已过期");
        }else{
            if(s.equals(registerInfo.getCode())){
                return  loginFeign.registerHtml(registerInfo);
            }
        }
        return R.fail("验证码错误");

    }

}

