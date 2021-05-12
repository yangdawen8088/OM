package com.zero2oneit.mall.system.dept.mapper;

import com.zero2oneit.mall.system.dept.entity.Dept;
import com.zero2oneit.mall.system.dept.query.DeptQueryObject;

import java.util.HashMap;
import java.util.List;

public interface DeptMapper {

    //加载所有部门数据
    List<Dept> selectDeptList();

    void add(Dept deptVO);

    void edit(Dept deptVO);

    int selectTotal(DeptQueryObject qo);

    List<HashMap<String,Object>> selectRows(DeptQueryObject qo);

    List<HashMap<String,Object>> getDeptByName(String keyword);

}
