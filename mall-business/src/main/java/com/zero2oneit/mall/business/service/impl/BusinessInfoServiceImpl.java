package com.zero2oneit.mall.business.service.impl;

import com.zero2oneit.mall.common.bean.business.BusinessInfo;
import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.business.mapper.BusinessInfoMapper;
import com.zero2oneit.mall.business.service.BusinessInfoService;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-23
 */
@Service
public class BusinessInfoServiceImpl extends ServiceImpl<BusinessInfoMapper, BusinessInfo> implements BusinessInfoService {

    @Autowired
    private BusinessInfoMapper businessInfoMapper;

    @Override
    public Suggest getBusinessByName(String keyword) {
        return new Suggest(businessInfoMapper.getBusinessByName(keyword));
    }
}