package com.zero2oneit.mall.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zero2oneit.mall.common.bean.log.LogDetail;

/**
 * Description: MQ对象json转换工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/3/24
 */
public class FastJsonConvertUtil<T> {

    public static String convertObjectToJSON(LogDetail log) {
        return JSON.toJSONString(log);
    }

    public static JSONObject toJsonObject(Object javaBean) {
        return JSONObject.parseObject(JSONObject.toJSON(javaBean).toString());
    }

    public static LogDetail convertJSONToObject(JSONObject json) {
        return JSONObject.toJavaObject(json, LogDetail.class);
    }

}