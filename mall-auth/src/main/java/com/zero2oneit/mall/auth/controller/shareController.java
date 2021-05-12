package com.zero2oneit.mall.auth.controller;


import com.alibaba.fastjson.JSON;

import com.github.wxpay.sdk.WXPayUtil;
import com.zero2oneit.mall.auth.config.WxAuthProperties;
import com.zero2oneit.mall.common.utils.R;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: shareController
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/11/21 10:48
 */
@RestController
@RequestMapping("/share/wx")
public class shareController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxAuthProperties wxAuthBean;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/wx")
    public R ajaxShare(@RequestBody Map data)   {
        log.info("进入分享方法");
        String oauthToken = redisTemplate.opsForValue().get("wx:share:accessToken");
        String jsapiTicket = redisTemplate.opsForValue().get("wx:share:jsapiTicket");
        if (oauthToken == null) {
            // 重新获取
            oauthToken = getAccessToken();
        }
        if (jsapiTicket == null) {
            // 重新获取
            jsapiTicket =getTicket(oauthToken);
        }
        String noncestr = WXPayUtil.generateNonceStr();
        Long timestamp = new Date().getTime() / 1000;
        String param = (String) data.get("url");
        log.info("参数打印："+ JSON.toJSONString(data));

        String signature = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp
                + "&url=" + param;
        log.info("分享链接："+ JSON.toJSONString(signature));
        signature = getSha1(signature);
        log.info("signature============："+ JSON.toJSONString(signature));
        Map<String, Object> wechatSign = new HashMap<String, Object>();
        wechatSign.put("timestamp", timestamp);
        wechatSign.put("noncestr", noncestr);
        wechatSign.put("signature", signature);
        wechatSign.put("appid", wxAuthBean.getWeChatClientId());
        log.info("返回参数打印："+ JSON.toJSONString(wechatSign));
        return R.ok(wechatSign);
    }


    // 获取token
    private String getAccessToken() {
        log.info("获取access_token");
        String access_token = "";
        String grant_type = "client_credential";//获取access_token填写client_credential

        //这个url链接地址和参数皆不能变  这个token是需要进行缓存的，因为它的调用次数每日是有限的。
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+wxAuthBean.getWeChatClientId()+"&secret="+wxAuthBean.getWeChatAppSecret();  //访问链接

        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes);
            JSONObject demoJson = JSONObject.fromObject(message);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("access_token:"+access_token);
    //    redisTemplate.opsForValue("wx:share:accessToken",access_token);
        redisTemplate.opsForValue().set("wx:share:accessToken", access_token,2, TimeUnit.HOURS);//向redis里存入数据和设置缓存时间
        return access_token;
    }

    // 获取ticket
    private String  getTicket(String access_token) {
        log.info("jsapiTicket");
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";// 这个url链接和参数不能变
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set("wx:share:jsapiTicket", ticket,2, TimeUnit.HOURS);//向redis里存入数据和设置缓存时间
        return ticket;
    }


    public  String getSha1(String str) {
        log.info("加密=======================");
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
