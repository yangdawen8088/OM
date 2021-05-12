package com.zero2oneit.mall.system.upgrade.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import com.zero2oneit.mall.system.base.utils.SessionBean;
import com.zero2oneit.mall.system.base.utils.UpgradeUtil;
import com.zero2oneit.mall.system.upgrade.mapper.UpgradeMapper;
import com.zero2oneit.mall.system.upgrade.service.UpgradeService;
import com.zero2oneit.mall.system.upgrade.vo.UpgradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Service
public class UpgradeServiceImpl implements UpgradeService {

    @Autowired
    private UpgradeMapper upgradeMapper;

    @Override
    public BoostrapDataGrid queryList(QueryObject qo) {
        //查询总记录数
        int total = upgradeMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : upgradeMapper.selectRows(qo));
    }

    @Override
    public void upgradeChangeStatus(UpgradeVO vo) {
        //设置缓存中系统运行状态
        int status = Integer.parseInt(vo.getStatus());
        UpgradeUtil.bind(status);
        //清除缓存中的session数据
        if(status == 0){
            for (Map.Entry<Long, HttpSession> sessionEntry : SessionBean.getUserSession().entrySet()) {
                HttpSession session = sessionEntry.getValue();
                session.invalidate();
            }
        }
        upgradeMapper.upgradeChangeStatus(vo);
    }

}
