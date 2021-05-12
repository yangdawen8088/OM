package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.MemberLeaders;
import com.zero2oneit.mall.common.query.member.memberLeadersObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MemberLeadersMapper;
import com.zero2oneit.mall.member.service.MemberLeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MemberLeadersServiceImpl extends ServiceImpl<MemberLeadersMapper, MemberLeaders> implements MemberLeadersService {

    @Autowired
    private MemberLeadersMapper memberLeadersMapper;

    @Override
    public BoostrapDataGrid memberLeaders(memberLeadersObject qo) {

       int total = memberLeadersMapper.total(qo);
       return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : memberLeadersMapper.LeadersRows(qo));
    }

    @Override
    public MemberLeaders findLeadersName(String memberId) {

        return memberLeadersMapper.findLeadersName(memberId);
    }
}
