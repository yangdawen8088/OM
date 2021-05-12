package com.zero2oneit.mall.generator.config;

import com.zero2oneit.mall.generator.mapper.GeneratorMapper;
import com.zero2oneit.mall.generator.mapper.MySQLGeneratorMapper;
import com.zero2oneit.mall.generator.mapper.OracleGeneratorMapper;
import com.zero2oneit.mall.generator.mapper.SQLServerGeneratorMapper;
import com.zero2oneit.mall.generator.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description:数据库配置
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11 15:49
 */
@Configuration
public class DbConfig {

    @Value("${om.database: mysql}")
    private String database;
    @Autowired
    private MySQLGeneratorMapper mySQLGeneratorMapper;
    @Autowired
    private OracleGeneratorMapper oracleGeneratorMapper;
    @Autowired
    private SQLServerGeneratorMapper sqlServerGeneratorMapper;

    @Bean
    @Primary
    public GeneratorMapper getGeneratorMapper(){
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorMapper;
        }else if("oracle".equalsIgnoreCase(database)){
            return oracleGeneratorMapper;
        }else if("sqlserver".equalsIgnoreCase(database)){
            return sqlServerGeneratorMapper;
        }else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }

}
