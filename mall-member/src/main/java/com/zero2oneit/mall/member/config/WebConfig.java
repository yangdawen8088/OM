package com.zero2oneit.mall.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    /**
     * 自定义拦截
     * InterceptorRegistry : 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //表单重复提交拦截
        /**
         * addpathPatterns():给拦截器添加拦截路径（默认值：/或/**）
         * excludePathPatterns():给拦截器添加放行路径
         */
        registry.addInterceptor(repeatSubmitInterceptor);
    }

}
