package com.zero2oneit.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.query.member.HyDistributionObject;
import com.zero2oneit.mall.common.bean.member.MembershipDistribution;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MembershipDistributionMapper;
import com.zero2oneit.mall.member.service.MembershipDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class MembershipDistributionServiceImpl extends ServiceImpl<MembershipDistributionMapper, MembershipDistribution> implements MembershipDistributionService {

    @Autowired
    private MembershipDistributionMapper distributionMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public BoostrapDataGrid distributionList(HyDistributionObject qo) {
        int tetal =distributionMapper.distributionTetal(qo);
        return new BoostrapDataGrid(tetal,tetal ==0 ? Collections.EMPTY_LIST: distributionMapper.distributionRecord(qo));
    }

    @Override
    public R openEdits(Map map) {
        distributionMapper.openEdits(map);
        return R.ok();
    }

    @Override
    public String hnProportion() {
        return distributionMapper.hnProportion();
    }

    @Override
    public MembershipDistribution yzScDistribution(Integer types) {
        return distributionMapper.yzScDistribution(types);
    }

    @Override
    public R queryShareProp() {
        JSONObject vers = JSONObject.parseObject(redisTemplate.opsForValue().get("shareProp"));
        Map<String,String> ver = JSON.toJavaObject(vers, Map.class);
        if (StringUtils.isEmpty(vers)){
            ver = distributionMapper.queryShareProp();
            redisTemplate.opsForValue().set("shareProp",JSONObject.toJSONString(ver),20, TimeUnit.MINUTES);
        }
        return  R.ok(ver);
    }


}
