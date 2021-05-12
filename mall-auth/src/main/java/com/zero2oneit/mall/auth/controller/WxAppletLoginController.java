package com.zero2oneit.mall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zero2oneit.mall.auth.config.WxAuthProperties;
import com.zero2oneit.mall.auth.feign.member.MemberLoginFeign;
import com.zero2oneit.mall.auth.utils.WXCore;
import com.zero2oneit.mall.common.bean.auth.AppWxLoginObject;
import com.zero2oneit.mall.common.utils.R;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 微信小程序授权登录
 * @Title: WxAppletLoginController
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/7/29 11:39
 */
@RestController
@RequestMapping("/applet/wxLogin")
public class WxAppletLoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberLoginFeign loginFeign;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final long  serialVersionUID=1L;
    @Autowired
    WxAuthProperties wxAuthBean;
    @RequestMapping(value = "/appeltlogin")
    public R appeltlogin(@RequestBody Map param){
        log.error(JSONArray.toJSONString(param));
        System.out.println(JSONArray.toJSONString(param));
        String code = (String) param.get("code");
        String encryptedData = (String) param.get("encryptedData");
        String iv = (String) param.get("iv");
        String type = (String) param.get("type");
        String session_key=null;
        String openid=null;
        Map<String, String> appIdMap = wxAuthBean.getAppIdMap();
        Map<String, String> appSecretMap = wxAuthBean.getAppSecretMap();
        String appId = appIdMap.get(type);
        String appletSecret = appSecretMap.get(type);
        AppWxLoginObject appWxLoginObject= new  AppWxLoginObject ();
        //微信那边的接口，grant_type=authorization_code是固定的
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+ appId+
                "&secret="+appletSecret+"&js_code="+ code +"&grant_type=authorization_code";
        //发送请求给微信后端
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        InputStream inputStream=null;
        CloseableHttpResponse httpResponse=null;
        StringBuilder result=new StringBuilder();
        try {
            httpResponse=httpClient.execute(httpGet);
            HttpEntity entity=httpResponse.getEntity();
            inputStream=entity.getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line); //这里需要使用fastjson来提取一下内容
                JSONObject jsonObject= JSON.parseObject(line);
                 openid = jsonObject.getString("openid");
                 session_key = jsonObject.getString("session_key");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String decrypt = WXCore.decrypt(appId, encryptedData, session_key, iv);
        Map map = JSONArray.parseObject(decrypt, Map.class);
        appWxLoginObject.setType(type);
        appWxLoginObject.setAvatarUrl(param.get("avatarUrl")+"");
        appWxLoginObject.setGender(Integer.valueOf(param.get("gender")+""));
        appWxLoginObject.setNickname(param.get("nickname")+"");
        appWxLoginObject.setWxApplteOpenid(openid);
        appWxLoginObject.setPhone(map.get("phoneNumber")+"");
        appWxLoginObject.setInvitation(param.get("invitation")+"");
        return  loginFeign.appeltWxauth(appWxLoginObject);
    }
}
