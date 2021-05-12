package com.zero2oneit.mall.system.dictionary.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.dictionary.query.CodeQueryObject;
import com.zero2oneit.mall.system.dictionary.service.CodeService;
import com.zero2oneit.mall.system.dictionary.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    @RequestMapping("/codePage")
    @ResponseBody
    public BoostrapDataGrid codePage(@RequestBody CodeQueryObject qo) {//VO : value-object 值对象
        return codeService.queryList(qo);
    }

    //添加或编辑角色信息
    @RequestMapping("/addOrEditCode")
    @ResponseBody
    public R addOrEditCode(CodeVO codeVO) {//VO : value-object 值对象
        codeService.addOrEditCode(codeVO);
        return R.ok("操作成功");
    }

    @RequestMapping("/changeCodeStatus")
    @ResponseBody
    public R changeCodeStatus(String status, String ids) {//VO : value-object 值对象
        codeService.changeCodeStatus(status, ids);
        return R.ok("改变属性状态成功");
    }

}
