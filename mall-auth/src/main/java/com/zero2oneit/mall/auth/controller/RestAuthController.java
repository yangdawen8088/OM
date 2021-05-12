package com.zero2oneit.mall.auth.controller;

/**
 * web端
 * 微信开放平台登录
 * @Title: RestAuthController
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/7/3 16:28
 */


import com.zero2oneit.mall.auth.config.WxAuthProperties;
import com.zero2oneit.mall.auth.feign.member.MemberLoginFeign;
import com.zero2oneit.mall.common.bean.auth.AppWxLoginObject;
import com.zero2oneit.mall.common.utils.R;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("open/oauth")
public class RestAuthController {

    @Autowired
    WxAuthProperties wxAuthBean;

    @Autowired
    MemberLoginFeign loginFeign;

    /**
     * 获取登录认证链接
     * @param response
     * @throws IOException
     */
    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        System.out.println(authRequest.authorize(AuthStateUtils.createState()));
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }
    /**
     * 扫码回调接口
     * 验证微信openid
     * @param callback
     * @return
     */
    @RequestMapping("/callback")
    public R login(AuthCallback callback) {
        System.out.println("回调");
        AuthRequest authRequest = getAuthRequest();
        AuthResponse login = authRequest.login(callback);
        AppWxLoginObject loginInfo= new AppWxLoginObject();
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

    /**
     * 构建链接
     * @return
     */
    private AuthRequest getAuthRequest() {
        return new AuthWeChatOpenRequest(AuthConfig.builder()
                .clientId(wxAuthBean.getOpenClientId())
                .clientSecret(wxAuthBean.getOpenAppSecret())
                .redirectUri(wxAuthBean.getOpenRedirectUri())
                .build());

    }

  /*  private AuthRequest getAuthRequest() {
        return new AuthWeChatOpenRequest(AuthConfig.builder()
                .clientId("wx564e7eaa68e8466e")
                .clientSecret("ef1b3bb2374a21f15bc2f9141577abdb")
                .redirectUri("http://heiye.natapp1.cc/open/oauth/callback")
                .build());
    }
*/

}
