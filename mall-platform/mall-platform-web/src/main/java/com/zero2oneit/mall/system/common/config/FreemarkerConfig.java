package com.zero2oneit.mall.system.common.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * freemarker配置shiro
 */

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        freemarker.template.Configuration configuration = freeMarkerConfigurer.createConfiguration();
        configuration.setDefaultEncoding("UTF-8");
        //这里可以添加其他共享变量 比如sso登录地址
        configuration.setSharedVariable("shiro", new ShiroTags());

        Properties settings = new Properties();
        settings.setProperty("suffix", ".html");
        settings.setProperty("content-type", "text/html");
        settings.setProperty("cache", "false");
        settings.setProperty("enabled", "true");
        freeMarkerConfigurer.setFreemarkerSettings(settings);

        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }

}
