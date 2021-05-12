package com.zero2oneit.mall.member.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberFans;
import com.zero2oneit.mall.common.query.member.MemberFansObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
public interface MemberFansService extends IService<MemberFans> {

    BoostrapDataGrid memberFans(MemberFansObject qo);

    BoostrapDataGrid memberFansList(MemberFansObject qo);

    int queryFansCount(MemberFansObject qo);


    BoostrapDataGrid fansRowsGrade(MemberFansObject qo);
}

