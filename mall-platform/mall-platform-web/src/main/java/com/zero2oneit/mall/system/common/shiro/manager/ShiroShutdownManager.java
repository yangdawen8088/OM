package com.zero2oneit.mall.system.common.shiro.manager;

import com.zero2oneit.mall.system.common.shiro.session.SpringSessionValidationScheduler;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 确保应用退出时能关闭后台线程
 */
@Component
public class ShiroShutdownManager {

    private static final Logger logger = LoggerFactory.getLogger(ShiroShutdownManager.class);

    @Autowired(required = false)
    private SpringSessionValidationScheduler springSessionValidationScheduler;

    @Autowired(required = false)
    private EhCacheManager ehCacheManager;

    @PreDestroy
    public void destroy() {
        shutdownSpringSessionValidationScheduler();
        shutdownEhCacheManager();
    }

    /**
     * 停止Seesion会话检查
     */
    private void shutdownSpringSessionValidationScheduler() {
        if (springSessionValidationScheduler != null && springSessionValidationScheduler.isEnabled()) {
            try {
                logger.info("====关闭会话验证任务====");
                springSessionValidationScheduler.disableSessionValidation();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    private void shutdownEhCacheManager() {
        try {
            logger.info("====关闭缓存====");
            if (ehCacheManager != null) {
                CacheManager cacheManager = ehCacheManager.getCacheManager();
                cacheManager.shutdown();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
