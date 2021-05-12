package com.zero2oneit.mall.member.api;

import com.alibaba.fastjson.JSONObject;
import com.zero2oneit.mall.common.annotion.RepeatSubmit;
import com.zero2oneit.mall.common.utils.HttpClientUtils;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.config.WxAuthProperties;
import com.zero2oneit.mall.member.service.MemberInfoService;
import com.zero2oneit.mall.member.utils.WXCore;
import com.zero2oneit.mall.member.utils.WxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Description: 用户登录、注册、忘记密码
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/18
 */
@RestController
@RequestMapping("/api/member")
public class AuthApi {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WxAuthProperties prop;

    /**
     * 账号、密码注册
     * @param
     * @return
     */
    @PostMapping("/register")
    @RepeatSubmit
    public R register(@RequestBody Map<String, String> params){
        String vCode = redisTemplate.opsForValue().get("om:sms:verify:code:" + params.get("phone"));
        //验证码校验
        if(params.get("code").equals(vCode)){
            //判断账号是否存在
            boolean isExist = memberInfoService.checkAccount(params.get("phone"));
            if(isExist){
                memberInfoService.register(params);
                return R.ok("注册成功");
            }
            return R.fail("该账号已被注册");
        }
        return R.fail("验证输入不正确");
    }

    /**
     * 账号、密码登录
     * @param
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> params){
        return memberInfoService.login(params);
    }

    /**
     * 小程序一键登录
     * @param
     * @return
     */
    @PostMapping("/appletLogin")
    public R appletLogin(@RequestBody WxInfo info){
        String appUrl = prop.getAppUrl();
        String url = MessageFormat.format(appUrl, prop.getAppletId(), prop.getAppletSecret(), info.getCode());
        String result = HttpClientUtils.doGet(url);
        Map<String, ?> map = jsonStr2Map(result);
        String sessionKey = (String) map.get("session_key");
        String openid = (String) map.get("openid");

        String decrypt = WXCore.decrypt(prop.getAppletId(), info.getEncryptedData(), sessionKey, info.getIv());
        Map<String, ?> maps = jsonStr2Map(decrypt);
        info.setType("applet");
        info.setOpenId(openid);
        info.setPhoneNumber((String) maps.get("phoneNumber"));

        return memberInfoService.appletLogin(info);
    }

    public Map<String,?> jsonStr2Map(String str){
        return JSONObject.parseObject(str);
    }

    /**
     * 修改或忘记登录密码
     * @param
     * @return
     */
    @PostMapping("/editOrForget")
    public R editOrForget(@RequestBody Map<String, String> params){
        String code = redisTemplate.opsForValue().get("om:sms:verify:code:" + params.get("phone"));
        //验证码校验
        if(params.get("code").equals(code)){
            //判断账号是否存在
            boolean isExist = memberInfoService.checkAccount(params.get("phone"));
            if(!isExist){
                memberInfoService.editOrForget(params);
                return R.ok("修改密码成功");
            }
            return R.fail("该手机号码不存在");
        }
        return R.fail("验证输入不正确");
    }

    /**
     * 退出登录
     * @param
     * @return
     */
    @PostMapping("/logout")
    public R logout(@RequestBody String memerId){
        //删除redis中对应用户的数据
        redisTemplate.delete("om:token:applet:" + memerId);
        redisTemplate.opsForHash().delete("om:member:info",memerId);
        redisTemplate.opsForHash().delete("om:member:accounts",memerId);
        return R.ok("退出登录成功");
    }

}
