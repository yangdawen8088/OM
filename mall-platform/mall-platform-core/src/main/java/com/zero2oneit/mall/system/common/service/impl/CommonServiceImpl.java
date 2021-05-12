package com.zero2oneit.mall.system.common.service.impl;

import com.zero2oneit.mall.common.utils.select2.Select2;
import com.zero2oneit.mall.system.common.mapper.CommonMapper;
import com.zero2oneit.mall.system.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Select2> select2(String codeTable, String codeField) {
        return commonMapper.select2(codeTable, codeField);
    }

}
