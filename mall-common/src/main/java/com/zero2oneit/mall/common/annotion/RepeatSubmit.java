package com.zero2oneit.mall.common.annotion;

import java.lang.annotation.*;

/**
 * Description: 自定义注解防止表单重复提交
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

}