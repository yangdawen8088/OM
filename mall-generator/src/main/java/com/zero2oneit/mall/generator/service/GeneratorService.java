package com.zero2oneit.mall.generator.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zero2oneit.mall.generator.mapper.GeneratorMapper;
import com.zero2oneit.mall.generator.utils.GenUtils;
import com.zero2oneit.mall.generator.utils.PageUtils;
import com.zero2oneit.mall.generator.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Description: 业务处理
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11 16:50
 */
@Service
public class GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    public PageUtils queryList(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = generatorMapper.queryList(query);

        return new PageUtils(list, (int)page.getTotal(), query.getLimit(), query.getPage());
    }

    public Map<String, String> queryTable(String tableName) {
        return generatorMapper.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorMapper.queryColumns(tableName);
    }

    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for(String tableName : tableNames){
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}