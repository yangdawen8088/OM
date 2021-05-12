package com.zero2oneit.mall.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 统一处理请求响应结果
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
@Data
@NoArgsConstructor
public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否响应成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public R(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.msg = message;
    }

    public R(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    /**
     * 响应成功无参数
     * @return
     */
    public static R ok(){
        return new R(true, 200, "success");
    }

    /**
     * 响应成功带参数
     * @param code ：自定义响应状态码
     * @return
     */
    public static R ok(Integer code){
        return new R(true, code, "success");
    }

    /**
     * 响应成功带参数
     * @param message ：自定义响应消息
     * @return
     */
    public static R ok(String message){
        return new R(true, 200, message);
    }

    /**
     * 响应成功带参数
     * @param data ：响应数据
     * @return
     */
    public static R ok(Object data){
        return new R(true, 200, "success", data);
    }

    /**
     * 响应成功带参数
     * @param code ：自定义响应状态码
     * @param message ：自定义响应消息
     * @return
     */
    public static R ok(Integer code, String message){
        return new R(true, 200, message);
    }

    /**
     * 响应成功带参数
     * @param code ：自定义响应状态码
     * @param data ：响应数据
     * @return
     */
    public static R ok(Integer code, Object data){
        return new R(true, code, "success", data);
    }

    /**
     * 响应成功带参数
     * @param message ：自定义响应消息
     * @param data ：响应数据
     * @return
     */
    public static R ok(String message, Object data){
        return new R(true, 200, message, data);
    }

    /**
     * 响应成功带参数
     * @param code ：自定义响应状态码
     * @param message ：自定义响应消息
     * @param message ：响应数据
     * @return
     */
    public static R ok(Integer code, String message, Object data){
        return new R(true, code, message, data);
    }

    /**
     * 响应失败无参数
     * @return
     */
    public static R fail(){
        return new R(false, 500, "server error");
    }

    /**
     * 响应失败带参数
     * @param code ：自定义响应状态码
     * @return
     */
    public static R fail(Integer code){
        return new R(false, code, "server error");
    }

    /**
     * 响应失败带参数
     * @param message ：自定义响应消息
     * @return
     */
    public static R fail(String message){
        return new R(false, 500, message);
    }

    /**
     * 响应失败带参数
     * @param code ：自定义响应状态码
     * @param message ：自定义响应消息
     * @return
     */
    public static R fail(Integer code, String message){
        return new R(false, code, message);
    }

}
