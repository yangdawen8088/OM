package com.zero2oneit.mall.system.base.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放session bean
 */
public class SessionBean {

    public static Map<String, String> sessions = new HashMap<String,String>();

    public static Map<String, String> getSessions() {
        return sessions;
    }

    public static void setSessions(Map<String, String> sessions) {
        SessionBean.sessions = sessions;
    }

    public static Map<Long, HttpSession> userSession = new HashMap<>();

    public static Map<Long, HttpSession> getUserSession() {
        return userSession;
    }

    public static void setUserSession(Map<Long, HttpSession> userSession) {
        SessionBean.userSession = userSession;
    }
}
