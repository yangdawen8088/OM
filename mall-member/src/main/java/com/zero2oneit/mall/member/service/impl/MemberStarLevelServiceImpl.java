package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.MemberStarLevel;
import com.zero2oneit.mall.common.query.member.MemberStarLevelObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MemberStarLevelMapper;
import com.zero2oneit.mall.member.service.MemberStarLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Service
public class MemberStarLevelServiceImpl extends ServiceImpl<MemberStarLevelMapper, MemberStarLevel> implements MemberStarLevelService {


    @Autowired
    private MemberStarLevelMapper memberStarLevelMapper;

    @Override
    @Transactional
    public R addManageAccount(BigDecimal two, BigDecimal three, BigDecimal four) {
        memberStarLevelMapper.addManageAccount(2,two);
        memberStarLevelMapper.addManageAccount(3,three);
        memberStarLevelMapper.addManageAccount(4,four);
        return R.ok();
    }



    @Override
    public R  findByIdStarLevelMp(Map map) {
        Integer id = (Integer) map.get("id");
        MemberStarLevel starLevel = new MemberStarLevel();
        starLevel.setId(Long.valueOf(id));
        MemberStarLevel memberStarLevel = memberStarLevelMapper.selectById(starLevel);
        return  R.ok(memberStarLevel);
    }

    @Override
    public BoostrapDataGrid starlevelList(MemberStarLevelObject qo) {
        int total= memberStarLevelMapper.starlevelTotal(qo);
        return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST:memberStarLevelMapper.starlevelRow(qo));
    }



}
