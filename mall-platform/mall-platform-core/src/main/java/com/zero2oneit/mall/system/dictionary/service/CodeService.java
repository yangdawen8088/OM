package com.zero2oneit.mall.system.dictionary.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.dictionary.query.CodeQueryObject;
import com.zero2oneit.mall.system.dictionary.vo.CodeVO;

public interface CodeService {

    BoostrapDataGrid queryList(CodeQueryObject qo);

    void addOrEditCode(CodeVO codeVO);

    void changeCodeStatus(String status, String ids);

}
