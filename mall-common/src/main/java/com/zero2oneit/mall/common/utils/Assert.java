package com.zero2oneit.mall.common.utils;

import com.zero2oneit.mall.common.exception.CustomException;
import org.springframework.util.StringUtils;

/**
 * Description: 断言工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class Assert {

    /**
     * 判断对象是否为空
     * @param object 传进来的参数
     * @param message 响应对应的错误信息
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CustomException(message);
        }
    }

     /**
     * 断言对象不为null,如果为null，抛出异常
     * @param str ：断言的字符串
     * @param message ：异常信息
     */
    public static void notNull(String str, String message) {
        if (!StringUtils.hasLength(str)) {
            throw new CustomException(message);
        }
    }

    /**
     * 范围长度断言，不满足这个范围，抛出异常
     */
    public static void isRangeLength(String str , int min ,int max , String message){
        notNull(str,message);
        if(str.length() < min || str.length()  > max){
            throw new CustomException(message);
        }
    }
    public static void isLength(String str , int length, String message){
        notNull(str,message);
        if(str.length() != length){
            throw new CustomException(message);
        }
    }

    /**
     * 断言false
     */
    public static void isFlase(boolean tag , String message){
        if(tag){
            throw new CustomException(message);
        }
    }

    /**
     * 断言两个字符串相等
     */
    public static void isEquals(String str1 ,String str2, String message){
        notNull(str1 , message);
        notNull(str2 , message);
        if(!str1.equals(str2)){
            throw new CustomException(message);
        }
    }

    /**
     * isNotEquals 断言相等，如果为空，直接抛出异常
     * @param str1
     * @param str2
     * @param message
     */
    public static void isNotEquals(String str1 ,String str2, String message){
        notNull(str1 , message);
        notNull(str2 , message);
        if(str1.equals(str2)){
            throw new CustomException(message);
        }
    }

    /**
     * 判断字符串是否相等
     * @param expression 传进来的参数
     * @param message 响应对应的错误信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new CustomException(message);
        }
    }

}
