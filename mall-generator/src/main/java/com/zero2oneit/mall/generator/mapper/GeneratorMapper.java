package com.zero2oneit.mall.generator.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: 公共Mapper处理方法
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11 16:10
 */
public interface GeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
