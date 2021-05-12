package com.zero2oneit.mall.system.dept.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.system.dept.entity.Dept;
import com.zero2oneit.mall.system.dept.query.DeptQueryObject;

import java.util.List;

public interface DeptService {

    List<Dept> deptList();

    void add(Dept deptVO);

    void edit(Dept deptVO);

    BoostrapDataGrid queryList(DeptQueryObject qo);

    Suggest getDeptByName(String keyword);

}
