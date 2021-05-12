package com.zero2oneit.mall.common.utils.query;

import lombok.Data;

@Data
public class QueryObject {

    private int currentPage = 1;//当前页
    private int pageSize = 10;//每页显示多少条记录

    //获取数据库分页开始索引
    public int getStarIndex() {
        return (currentPage - 1) * pageSize;
    }

}
