package com.zero2oneit.mall.common.utils.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description: Boostrap表格数据封装工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/12
 */
@Getter
@NoArgsConstructor
public class BoostrapDataGrid implements Serializable {

    private static final long serialVersionUID = 1L;

    //两查
    private int total;//查询总记录数
    private List<?> rows;//查询数据

    //有数据情况
    public BoostrapDataGrid(int total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

}
