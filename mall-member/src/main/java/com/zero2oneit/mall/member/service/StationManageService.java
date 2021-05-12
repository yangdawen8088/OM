package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;

import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 10:52
 */
public interface StationManageService extends IService<StationManage> {

    R addOrEdit(StationManage stationManage);

    BoostrapDataGrid pageList(StationManageQueryObject qo);

    R stationStatusEdit(String value, String id);

    List<BaseTreeNode> tree();

    R checkByIds(String ids);

    R delByIds(String ids);
}
