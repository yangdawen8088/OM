package com.zero2oneit.mall.system.dictionary.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.dictionary.mapper.CodeMapper;
import com.zero2oneit.mall.system.dictionary.query.CodeQueryObject;
import com.zero2oneit.mall.system.dictionary.service.CodeService;
import com.zero2oneit.mall.system.dictionary.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public BoostrapDataGrid queryList(CodeQueryObject qo) {
        //查询总记录数
        int total = codeMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : codeMapper.selectRows(qo));
    }

    @Override
    public void addOrEditCode(CodeVO codeVO) {
        codeVO.setUserId(UserContext.getCurrentUser(UserContext.SYS_USER).getId());
        if (codeVO.getId() == null){//添加
            codeMapper.add(codeVO);
        }else{//编辑
            codeMapper.edit(codeVO);
        }
    }

    @Override
    public void changeCodeStatus(String status, String ids) {
        codeMapper.changeCodeStatus(status, ids);
    }

}
