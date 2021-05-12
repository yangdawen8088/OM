package com.zero2oneit.mall.system.dept.service.impl;

import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.system.dept.entity.Dept;
import com.zero2oneit.mall.system.dept.mapper.DeptMapper;
import com.zero2oneit.mall.system.dept.query.DeptQueryObject;
import com.zero2oneit.mall.system.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> deptList() {
        List<Dept> deptList = deptMapper.selectDeptList();
        //将原数据转换成树形结构的数据格式
        return TreeUtils.assembleTree(deptList);
    }

    @Override
    public void add(Dept deptVO) {
        deptMapper.add(deptVO);
    }

    @Override
    public void edit(Dept deptVO) {
        deptMapper.edit(deptVO);
    }

    @Override
    public BoostrapDataGrid queryList(DeptQueryObject qo) {
        //查询总记录数
        int total = deptMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : deptMapper.selectRows(qo));
    }

    @Override
    public Suggest getDeptByName(String keyword) {
        return new Suggest(deptMapper.getDeptByName(keyword));
    }

}
