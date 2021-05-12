package com.zero2oneit.mall.system.base.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统升级维护工具
 */
public class UpgradeUtil {

    private final static Map<String, Integer> RUNTIME_STATUS = new ConcurrentHashMap<>();

    /**
     * RUNTIME_STATUS : 0-维护中 1-运行中
     */
    static {
        RUNTIME_STATUS.put("RUNTIME_STATUS", 1);
    }

    //绑定系统运行状态
    public static void bind(Integer statusCode){
        RUNTIME_STATUS.put("RUNTIME_STATUS", statusCode);
    }

    //获取系统运行状态
    public static Integer getRuntimeStatus(){
        return  RUNTIME_STATUS.get("RUNTIME_STATUS");
    }

}
