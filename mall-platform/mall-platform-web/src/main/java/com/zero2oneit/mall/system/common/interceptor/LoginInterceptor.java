package com.zero2oneit.mall.system.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.system.base.utils.SessionBean;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断如果当前请求的资源是一个方法 ，才做登录判断
        if (handler instanceof HandlerMethod) {

            //TODO 暂时使用session存放用户信息 后面使用Redis来保存
            User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);

            /**
             * 记录业务操作日志
             */
            //1.获取当前拦截到的方法对象  handler
            HandlerMethod method = (HandlerMethod) handler;
            // 判断是否是json请求
            boolean isJson = false;
            ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
            if (responseBody != null) {
                isJson = true;
            }

            //判断当前用户是否为空
            if (currentUser == null && isJson == false) {
                UserContext.sendRedirect(response);
                return false;
            }else if (currentUser == null && isJson == true){
                UserContext.renderString(response, JSON.toJSONString(R.ok("gq")));
                return false;
            }else {
                String sessionId = SessionBean.getSessions().get(currentUser.getAccount());//获取全局类SessionSave保存账户的静态sessionId
                String currentSessionId = request.getSession().getId();//获取当前的sessionId
                if (!currentSessionId.equals(sessionId)) {//如果两个sessionId不等，则当前账户强制下线，需要重新登录
                    UserContext.sendRedirect(response);
                    return false;
                } else {// 如果是同一账户session则放行请求
                    return true;
                }
            }
         }
        //放行
        return true;
    }

}