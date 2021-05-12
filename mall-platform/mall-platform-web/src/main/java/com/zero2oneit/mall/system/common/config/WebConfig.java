package com.zero2oneit.mall.system.common.config;

import com.zero2oneit.mall.system.common.interceptor.LoginInterceptor;
import com.zero2oneit.mall.system.common.interceptor.RepeatSubmitInterceptor;
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
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** swagger配置 */
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截
     * InterceptorRegistry : 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //平台登录拦截
        //registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login.html","/captcha","/login");

        //表单重复提交拦截
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**").excludePathPatterns("/favicon.ico");

    }

}
