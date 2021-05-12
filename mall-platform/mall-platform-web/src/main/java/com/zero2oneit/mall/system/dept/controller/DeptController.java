package com.zero2oneit.mall.system.dept.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import com.zero2oneit.mall.system.dept.entity.Dept;
import com.zero2oneit.mall.system.dept.query.DeptQueryObject;
import com.zero2oneit.mall.system.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/deptList")
    @ResponseBody
    public List<Dept> deptList() {
       return deptService.deptList();
    }

    //添加或编辑部门信息
    @RequestMapping("/addOrEditDept")
    @ResponseBody
    public R addOrEditDept(Dept deptVO) {//VO : value-object 值对象
        //添加
        if (deptVO.getId().length() == 0){
            deptService.add(deptVO);
        }else{//编辑
            deptService.edit(deptVO);
        }
        return R.ok("操作成功");
    }

    //根据部门IDS获取部门下的所有员工
    @RequestMapping("/getDeptUserByIds")
    @ResponseBody
    public BoostrapDataGrid getDeptUserByIds(@RequestBody DeptQueryObject qo) {//VO : value-object 值对象
        return deptService.queryList(qo);
    }

    //匹配部门信息
    @RequestMapping("/getDeptByName")
    @ResponseBody
    public Suggest getDeptByName(String keyword) {//VO : value-object 值对象
        return deptService.getDeptByName(keyword);
    }

}
