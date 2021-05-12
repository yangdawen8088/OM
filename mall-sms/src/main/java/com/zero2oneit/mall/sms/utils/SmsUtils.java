package com.zero2oneit.mall.sms.utils;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zero2oneit.mall.sms.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 短信工具类
 *
 * @author yjj
 * @date 2020/7/2 11:14
 */
@Slf4j
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    @Autowired
    private SmsProperties prop;

    /**
     * 发送短信
     *
     * @param phone ： 手机号码
     * @param param ：发送内容 {"code":xxxxxx}
     * @return
     */
    public boolean sendMsg(String phone, Map<String, String> param) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", prop.getAccessKeyId(), prop.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com"); //短信API产品域名（接口地址固定，无需修改）
        request.setSysVersion("xxxxxx");               //该API的版本号，格式为YYYY-MM-DD。
        request.setSysAction("xxxxx");                 //该API的是什么操作
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);       // 发送短信电话
        request.putQueryParameter("SignName", "xxxxxx");    //必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", "xxxxxxx"); //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
