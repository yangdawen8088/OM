package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.TreeUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import com.zero2oneit.mall.member.mapper.StationManageMapper;
import com.zero2oneit.mall.member.service.StationManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 10:52
 */
@Service
public class StationManageServiceImpl extends ServiceImpl<StationManageMapper, StationManage> implements StationManageService {

    @Autowired
    private StationManageMapper stationManageMapper;

    @Override
    public R addOrEdit(StationManage stationManage) {
        if (ObjectUtils.isEmpty(stationManage.getId())){
            return save(stationManage)?R.ok():R.fail();
        }else {
            return stationManageMapper.updateById(stationManage)>0?R.ok():R.fail();
        }
    }

    @Override
    public BoostrapDataGrid pageList(StationManageQueryObject qo) {
        int total = stationManageMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : stationManageMapper.selectAll(qo));
    }

    @Override
    public R stationStatusEdit(String value, String id) {
        StationManage stationManage = new StationManage();
        stationManage.setId(Long.valueOf(id));
        if ("0".equals(value)){
            stationManage.setStationStatus("1");
        }else if ("1".equals(value)){
            stationManage.setStationStatus("0");
        }
        return stationManageMapper.updateById(stationManage)>0?R.ok():R.fail();
    }

    @Override
    public List<BaseTreeNode> tree() {
        return TreeUtils.assembleTree(stationManageMapper.tree());
    }

    @Override
    public R checkByIds(String ids) {
        List<StationManage> manageList= new ArrayList<>(10);
        Arrays.asList(ids.split(",")).forEach(s -> {
            StationManage stationManage = new StationManage();
            stationManage.setGrantType(1);
            stationManage.setId(Long.valueOf(s));
            manageList.add(stationManage);
        });
        return updateBatchById(manageList)?R.ok():R.fail();
    }

    @Override
    public R delByIds(String ids) {
        return stationManageMapper.deleteBatchIds(Arrays.asList(ids.split(",")))>0?R.ok():R.fail();
    }
}
