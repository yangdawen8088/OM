package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.MemberFans;
import com.zero2oneit.mall.common.query.member.MemberFansObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MemberFansMapper;
import com.zero2oneit.mall.member.service.MemberFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class MemberFansServiceImpl extends ServiceImpl<MemberFansMapper, MemberFans> implements MemberFansService {

    @Autowired
    private MemberFansMapper memberFansMapper;

    @Override
    public BoostrapDataGrid memberFans(MemberFansObject qo) {
        int total=memberFansMapper.total(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : memberFansMapper.fansRows(qo));
    }

    @Override
    public BoostrapDataGrid memberFansList(MemberFansObject qo) {
        int total=memberFansMapper.total(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : memberFansMapper.memberFansRows(qo));
    }

    @Override
    public int queryFansCount(MemberFansObject qo) {
        return  memberFansMapper.total(qo);
    }

    @Override
    public BoostrapDataGrid fansRowsGrade(MemberFansObject qo) {
        int total=memberFansMapper.totalGrade(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST : memberFansMapper.fansRowsGrade(qo));
    }


}
