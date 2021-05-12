package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberStarInfo;
import com.zero2oneit.mall.common.query.member.MemberStarInfoObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


import java.math.BigDecimal;

/**
 * @author zhj
 * @create 2021-01-27
 * @description
 */
public interface MemberStarInfoService extends IService<MemberStarInfo> {

    R upgradeStar(String userId);

    R addConsume(String userId, BigDecimal consume);

    R findConsume(String userId);

    R upgradeStar2(String memberId);

    R findStarInfo(String userId);

    BoostrapDataGrid starInfolist(MemberStarInfoObject qo);

}

