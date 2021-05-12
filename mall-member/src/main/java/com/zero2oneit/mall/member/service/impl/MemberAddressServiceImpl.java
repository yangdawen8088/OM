package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.member.MemberAddress;
import com.zero2oneit.mall.common.query.member.MemberAddressQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MemberAddressMapper;
import com.zero2oneit.mall.member.service.MemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
@Service
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressService {

    @Autowired
    private MemberAddressMapper addressMapper;

    @Override
    public BoostrapDataGrid pageList(MemberAddressQueryObject qo) {
        int total = addressMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : addressMapper.selectAll(qo));
    }

    @Override
    public void setDefault(Long id, Long memberId) {
        addressMapper.setDefault(id, memberId);
    }

}