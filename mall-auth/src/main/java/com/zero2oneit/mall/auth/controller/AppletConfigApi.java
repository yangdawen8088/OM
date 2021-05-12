package com.zero2oneit.mall.auth.controller;



import com.zero2oneit.mall.auth.config.WxAuthProperties;
import com.zero2oneit.mall.common.utils.R;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Title: AppletController
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/11/21 10:48
 */
@RestController
@RequestMapping("/appletConfig")
public class AppletConfigApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxAuthProperties wxAuthBean;

    @Autowired
    private StringRedisTemplate redisTemplate;




    // 获取token
    @RequestMapping(value = "/getAccessToken")
    private R getAccessToken() {
        log.info("获取access_token");
        String access_token = "";
        String grant_type = "client_credential";//获取access_token填写client_credential

        //这个url链接地址和参数皆不能变  这个token是需要进行缓存的，因为它的调用次数每日是有限的。
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+wxAuthBean.getAppletId()+"&secret="+wxAuthBean.getAppletSecret();  //访问链接

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
        redisTemplate.opsForValue().set("wx:applet:accessToken", access_token,2, TimeUnit.HOURS);//向redis里存入数据和设置缓存时间
        return R.ok(access_token);
    }


}
