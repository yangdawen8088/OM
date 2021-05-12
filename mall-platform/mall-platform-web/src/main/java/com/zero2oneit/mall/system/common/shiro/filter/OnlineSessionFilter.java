package com.zero2oneit.mall.system.common.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.common.shiro.constant.ShiroConstants;
import com.zero2oneit.mall.system.common.shiro.session.OnlineSessionDAO;
import com.zero2oneit.mall.system.common.shiro.util.ShiroUtils;
import com.zero2oneit.mall.system.shiro.entity.OnlineSession;
import com.zero2oneit.mall.system.shiro.enums.OnlineStatus;
import com.zero2oneit.mall.system.user.entity.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义访问控制
 */
public class OnlineSessionFilter extends AccessControlFilter {

    /**
     * 强制退出后重定向的地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
            if (isGuest == true) {
                User user = ShiroUtils.getSysUser();
                if (user != null) {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setLoginName(user.getAccount());
                    onlineSession.setAvatar(user.getHeadPhoto());
                    onlineSession.setDeptName(null);
                    onlineSession.markAttributeChanged();
                }
            }

            return onlineSession.getStatus() != OnlineStatus.OFF_LINE;
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }


    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        User user = ShiroUtils.getSysUser();
        // 判断是否是json请求
        boolean isJson = UserContext.isAjaxRequest((HttpServletRequest) request);
        if (user == null && isJson) {
            loginUrl = JSON.toJSONString(R.ok("gq"));
        }else if (user == null && isJson == false){
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("<script type='text/javascript'>");
            sb.append("window.parent.location = '/login.html';");
            sb.append("</script>");
            sb.append("</body>");
            sb.append("</html>");
            loginUrl = sb.toString();
        }

        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
