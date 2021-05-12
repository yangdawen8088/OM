package com.zero2oneit.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.member.MemberAccounts;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.snowflake.IdWorker;
import com.zero2oneit.mall.common.utils.snowflake.IdWorkerFactory;
import com.zero2oneit.mall.member.mapper.MemberInfoMapper;
import com.zero2oneit.mall.member.service.MemberAccountsService;
import com.zero2oneit.mall.member.service.MemberInfoService;
import com.zero2oneit.mall.member.utils.JwtUtils;
import com.zero2oneit.mall.member.utils.WxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements MemberInfoService {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private MemberAccountsService accountsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean checkAccount(String phone) {
        Integer count = memberInfoMapper.checkAccount(phone);
        return count.intValue() == 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(Map<String, String> params){
        IdWorker idworker = IdWorkerFactory.create(0,0);
        long memberId = idworker.nextId();
        params.put("member_id", String.valueOf(memberId));
        String md5Password = DigestUtils.md5DigestAsHex(params.get("password").getBytes());
        params.put("password", md5Password);
        //操作会员信息表
        memberInfoMapper.register(params);
        //操作会员账户表
        accountsService.add(memberId);
    }

    @Override
    public R login(Map<String, String> params) {
        String md5Password = DigestUtils.md5DigestAsHex(params.get("password").getBytes());
        QueryWrapper<MemberInfo> wrapper = new QueryWrapper();
        wrapper.eq("member_account",params.get("account"));
        wrapper.eq("member_password",md5Password);
        MemberInfo member = baseMapper.selectOne(wrapper);
        if(member != null){
            String token = jwtUtils.createJwtToken(member.getMemberId(), "applet");
            member.setToken(token);
            //将用户信息设置到redis中
            redisTemplate.opsForValue().set("om:token:applet:" + member.getMemberId(), token, 1, TimeUnit.DAYS);
            redisTemplate.opsForHash().put("om:member:info", member.getMemberId(), JSONArray.toJSONString(member));

            QueryWrapper<MemberAccounts> wrappers = new QueryWrapper();
            wrapper.eq("member_id", member.getMemberId());
            MemberAccounts accounts = accountsService.getOne(wrappers);
            redisTemplate.opsForHash().put("om:member:accounts", member.getMemberId(), JSON.toJSONString(accounts));

            return R.ok("登录成功", member);
        }
        return R.fail("账号或密码输入不正确");
    }

    @Override
    public void editOrForget(Map<String, String> params) {
        String md5Password = DigestUtils.md5DigestAsHex(params.get("password").getBytes());
        params.put("password", md5Password);
        memberInfoMapper.editOrForget(params);
    }

    @Override
    public void setPayPwd(Map<String, String> params) {
        String md5Password = DigestUtils.md5DigestAsHex(params.get("payPwd").getBytes());
        params.put("payPwd", md5Password);
        memberInfoMapper.setPayPwd(params);
    }

    @Override
    public void editPayPwd(Map<String, String> params) {
        String md5Password = DigestUtils.md5DigestAsHex(params.get("newPayPwd").getBytes());
        params.put("payPwd", md5Password);
        memberInfoMapper.setPayPwd(params);
    }

    @Override
    public R edit(Map<String, String> params) {
        String memberId = params.get("memberId");
        memberInfoMapper.edit(params);
        //更新redis
        QueryWrapper<MemberInfo> wrapper = new QueryWrapper();
        wrapper.eq("member_id",params.get("memberId"));
        MemberInfo member = baseMapper.selectOne(wrapper);
        redisTemplate.opsForValue().set("om:member:info:" + member.getMemberId(), JSONArray.toJSONString(member));
        return R.ok("编辑个人信息成功", member);
    }

    @Override
    public R appletLogin(WxInfo info) {

        QueryWrapper<MemberInfo> wrapper = new QueryWrapper();
        wrapper.eq("member_account", info.getPhoneNumber());
        MemberInfo member = baseMapper.selectOne(wrapper);
        if(member == null){
            IdWorker idworker = IdWorkerFactory.create(0,0);
            long memberId = idworker.nextId();
            //操作会员信息表
            info.setMemberId(memberId);
            memberInfoMapper.addUser(info);
            //操作会员账户表
            accountsService.add(memberId);
            //查询会员对象
            wrapper.eq("wx_applte_openid", info.getOpenId());
            member = baseMapper.selectOne(wrapper);
        }else{
            if(StringUtils.isBlank(member.getWxApplteOpenid())){
                memberInfoMapper.editUser(info);
                wrapper.eq("member_account", info.getPhoneNumber());
                member = baseMapper.selectOne(wrapper);
            }
        }

        String token = jwtUtils.createJwtToken(member.getMemberId(), "applet");
        member.setToken(token);
        //将用户信息设置到redis中
        redisTemplate.opsForValue().set("om:token:applet:" + member.getMemberId(), token, 5, TimeUnit.DAYS);
        redisTemplate.opsForHash().put("om:member:info", member.getMemberId(), JSONArray.toJSONString(member));

        QueryWrapper<MemberAccounts> wrappers = new QueryWrapper();
        wrapper.eq("member_id", member.getMemberId());
        MemberAccounts accounts = accountsService.getOne(wrappers);
        redisTemplate.opsForHash().put("om:member:accounts", member.getMemberId(), JSON.toJSONString(accounts));

        return R.ok("登录成功", member);
    }

    @Override
    public boolean check(String memberAccount, String password) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        int i = memberInfoMapper.check(memberAccount, md5Password);
        return i != 0;
    }

    /* -----------------  后端接口  ---------------------- */

    @Override
    public BoostrapDataGrid pageList(InfoQueryObject qo) {
        //查询总记录数
        int total = memberInfoMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : memberInfoMapper.selectRows(qo));
    }

    @Override
    public R resetPwd(String memberId) {
        String md5Password = DigestUtils.md5DigestAsHex("888888".getBytes());
        memberInfoMapper.resetPwd(md5Password, memberId);
        return R.ok("重置登录密码成功");
    }

}
