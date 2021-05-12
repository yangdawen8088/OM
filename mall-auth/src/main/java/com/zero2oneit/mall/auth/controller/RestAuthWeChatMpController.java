package com.zero2oneit.mall.auth.controller;



import com.zero2oneit.mall.auth.config.WxAuthProperties;
import com.zero2oneit.mall.common.utils.R;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 微信公众号平台登录
 * @Title: RestAuthWeChatMpController
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/7/3 16:52
 */

@RestController
@RequestMapping("/wechat/oauth")
public class RestAuthWeChatMpController{


    @Autowired
    WxAuthProperties wxAuthBean;

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        System.out.println(authRequest.authorize(AuthStateUtils.createState()));
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public R login(AuthCallback callback) {  AuthRequest authRequest = getAuthRequest();
        AuthResponse login = authRequest.login(callback);
        Map res=new HashMap();
        if(login.getCode() == 2000 ){
            AuthUser data = (AuthUser)login.getData();
            AuthToken token =  data.getToken();
            res.put("nickname",data.getNickname());
            res.put("openId",token.getOpenId());
            res.put("avatar",data.getAvatar());
        }
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(res));
        return R.ok(res);
    }

    private AuthRequest getAuthRequest() {
        return new AuthWeChatMpRequest(AuthConfig.builder()
                .clientId(wxAuthBean.getWeChatClientId())
                .clientSecret(wxAuthBean.getWeChatAppSecret())
                .redirectUri(wxAuthBean.getWeChatRedirectUri())
                .build());
    }


}
