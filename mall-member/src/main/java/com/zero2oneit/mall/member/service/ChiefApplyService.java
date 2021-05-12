package com.zero2oneit.mall.member.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-27
 */
public interface ChiefApplyService extends IService<ChiefApply> {

    BoostrapDataGrid chiefList(ChiefManageQueryObject qo);

    R chiefStatusEdit(String status, String id);

    R chiefByIds(Object chiefApplies);

    R addOrEdit(ChiefApply chiefApply);

}

