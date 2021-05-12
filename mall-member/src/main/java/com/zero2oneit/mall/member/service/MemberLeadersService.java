package com.zero2oneit.mall.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberLeaders;
import com.zero2oneit.mall.common.query.member.memberLeadersObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
public interface MemberLeadersService extends IService<MemberLeaders> {

    BoostrapDataGrid memberLeaders(memberLeadersObject qo);

    MemberLeaders findLeadersName(String memberId);

}

