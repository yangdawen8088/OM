package com.zero2oneit.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.ChiefApplyMapper;
import com.zero2oneit.mall.member.mapper.MemberInfoMapper;
import com.zero2oneit.mall.member.service.ChiefApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-27
 */
@Service
public class ChiefApplyServiceImpl extends ServiceImpl<ChiefApplyMapper, ChiefApply> implements ChiefApplyService {

    @Autowired
    private ChiefApplyMapper chiefApplyMapper;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Override
    public BoostrapDataGrid chiefList(ChiefManageQueryObject qo) {
        int total = chiefApplyMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total ==0 ? Collections.EMPTY_LIST : chiefApplyMapper.selectAll(qo));
    }

    @Override
    public R chiefStatusEdit(String status, String id) {
        ChiefApply chiefApply = new ChiefApply();
        chiefApply.setId(Long.valueOf(id));
        if ("0".equals(status)){
            chiefApply.setSwitchId(1);
        }else if ("1".equals(status)){
            chiefApply.setSwitchId(0);
        }
        return chiefApplyMapper.updateById(chiefApply)>0? R.ok(): R.fail();
    }

    @Override
    public R chiefByIds(Object chiefApplies) {
        List<Map> maps= JSON.parseObject(JSON.toJSONString(chiefApplies), List.class);
        for (Map map : maps) {
            chiefApplyMapper.updateStaIdById(map.get("id").toString());
            memberInfoMapper.updateStaIdByMemberId(map.get("memberId").toString(),map.get("id").toString());
        }
        return R.ok("审核成功");
    }

    /**
     * 添加 或 编辑
     * @param chiefApply
     * @return
     */
    @Override
    public R addOrEdit(ChiefApply chiefApply) {
        if(chiefApply.getId()==null){
            chiefApply.setStatusId(1);
            chiefApplyMapper.insert(chiefApply);
        }else {
            chiefApplyMapper.updateById(chiefApply);
        }
        return R.ok("保存成功");
    }
}
