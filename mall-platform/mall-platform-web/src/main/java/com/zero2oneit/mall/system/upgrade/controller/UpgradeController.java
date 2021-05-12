package com.zero2oneit.mall.system.upgrade.controller;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.upgrade.service.UpgradeService;
import com.zero2oneit.mall.system.upgrade.vo.UpgradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpgradeController {

    @Autowired
    private UpgradeService upgradeService;

    @RequestMapping("/upgradePage")
    public BoostrapDataGrid upgradePage(@RequestBody QueryObject qo) {//VO : value-object 值对象
        return upgradeService.queryList(qo);
    }

    //更改系统运行状态
    @RequestMapping("/upgradeChangeStatus")
    public R upgradeChangeStatus(UpgradeVO vo) {//VO : value-object 值对象
        upgradeService.upgradeChangeStatus(vo);
        return R.ok("操作成功");
    }

}
