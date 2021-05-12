package com.zero2oneit.mall.system.base.utils;

import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.system.user.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户上下文工具
 */
public class UserContext {

    /**
     * 平台内系统用户登录的唯一标志
     */
    public static final String SYS_USER = "system";

    /**
     * 验证码
     */
    public static final String AUTH_CODE = "authCode";

    /**
     * 获取请求对象
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取响应对象
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * 获取当前（session）用户信息
     * @param loginUser
     * @return
     */
    public static User getCurrentUser(String loginUser) {
        return (User) getSession().getAttribute(loginUser);
    }

    /**
     * 设置当前（session）用户信息
     * @param loginUser
     * @return
     */
    public static void setCurrentUser(User loginUser) {
        getSession().setAttribute(SYS_USER, loginUser);
    }

    /**
     * 获取验证码
     */
    public static String getAuthCode(String authCode) {
        return (String) getSession().getAttribute(authCode);
    }

    /**
     * 设置验证码
     */
    public static void setAuthCode(String authCode) {
        getSession().setAttribute(AUTH_CODE, authCode);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转登录页面
     *
     * @param response 渲染对象
     * @return null
     */
    public static String sendRedirect(HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html; charset=UTF-8");
            out.println("<html><head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script type='text/javascript'>");
            out.println("window.parent.location = '/login.html?flag=1';");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }
        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }
        String ajax = request.getParameter("__ajax");
        return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
    }

}
