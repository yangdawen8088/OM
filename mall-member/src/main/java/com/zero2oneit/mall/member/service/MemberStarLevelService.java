package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberStarLevel;
import com.zero2oneit.mall.common.query.member.MemberStarLevelObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhj
 * @create 2021-01-27
 * @description
 */
public interface MemberStarLevelService extends IService<MemberStarLevel> {


    R addManageAccount(BigDecimal two, BigDecimal three, BigDecimal four);

    R findByIdStarLevelMp(Map map);

    BoostrapDataGrid starlevelList(MemberStarLevelObject qo);

}

