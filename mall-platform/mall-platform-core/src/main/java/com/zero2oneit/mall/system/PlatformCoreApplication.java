package com.zero2oneit.mall.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description: 平台主程序启动类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/13
 */
@EnableTransactionManagement
@MapperScan("com.zero2oneit.mall.system.*.mapper")
public class PlatformCoreApplication {

}
