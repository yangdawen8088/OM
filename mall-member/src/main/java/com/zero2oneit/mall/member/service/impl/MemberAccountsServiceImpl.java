package com.zero2oneit.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.*;
import com.zero2oneit.mall.common.utils.DateUtil;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.member.mapper.*;
import com.zero2oneit.mall.member.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MemberAccountsServiceImpl extends ServiceImpl<MemberAccountsMapper, MemberAccounts> implements MemberAccountsService {

    @Autowired
    private MemberAccountsMapper accountsMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void add(long memberId) {
        accountsMapper.add(memberId);
    }

}
