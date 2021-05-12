package com.zero2oneit.mall.system.upgrade.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.upgrade.vo.UpgradeVO;

public interface UpgradeService {

    BoostrapDataGrid queryList(QueryObject qo);

    void upgradeChangeStatus(UpgradeVO vo);

}
