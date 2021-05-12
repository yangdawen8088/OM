package com.zero2oneit.mall.system.common.config;

import com.zero2oneit.mall.common.utils.xss.XssFilter;
import com.zero2oneit.mall.system.common.filter.ImageFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * Filter配置
 */
@Configuration
public class FilterConfig {

    /**
     * 图片过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean imageFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ImageFilter());
        registration.addUrlPatterns(StringUtils.split("/upload/*", ","));
        registration.setName("imageFilter");
        //registration.setOrder(Integer.MAX_VALUE);
        Map<String, String> initParameters = new HashMap<String, String>();
        //initParameters.put("excludes", excludes);
        initParameters.put("enabled", "true");
        registration.setInitParameters(initParameters);
        return registration;
    }


    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split("/system/*,/monitor/*,/tool/*", ","));
        registration.setName("xssFilter");
        //registration.setOrder(Integer.MAX_VALUE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", "/system/notice/*");//排除链接（多个用逗号分隔）
        initParameters.put("enabled", "true");
        registration.setInitParameters(initParameters);
        return registration;
    }

}
