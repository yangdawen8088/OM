package com.zero2oneit.mall.common.annotion;

import com.zero2oneit.mall.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * Description:自定义注解记录操作日志记录
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperateLog {

    /**
     * 模块,例如:"修改菜单"
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

}
