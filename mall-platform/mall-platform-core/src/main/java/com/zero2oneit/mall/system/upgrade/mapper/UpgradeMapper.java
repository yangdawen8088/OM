package com.zero2oneit.mall.system.upgrade.mapper;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.upgrade.vo.UpgradeVO;

import java.util.HashMap;
import java.util.List;

public interface UpgradeMapper {

    int selectTotal(QueryObject qo);

    List<HashMap<String,Object>> selectRows(QueryObject qo);

    void upgradeChangeStatus(UpgradeVO vo);

}
