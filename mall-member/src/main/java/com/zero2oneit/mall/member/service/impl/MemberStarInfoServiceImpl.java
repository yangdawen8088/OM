package com.zero2oneit.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zero2oneit.mall.common.bean.member.*;
import com.zero2oneit.mall.common.query.member.MemberStarInfoObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.mapper.MemberStarInfoMapper;
import com.zero2oneit.mall.member.mapper.MemberStarLevelMapper;
import com.zero2oneit.mall.member.service.MemberInfoService;
import com.zero2oneit.mall.member.service.MemberLeadersService;
import com.zero2oneit.mall.member.service.MemberStarInfoService;
import com.zero2oneit.mall.member.service.MemberStarLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MemberStarInfoServiceImpl extends ServiceImpl<MemberStarInfoMapper, MemberStarInfo> implements MemberStarInfoService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberStarLevelService memberStarLevelService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberLeadersService memberLeadersService;

    @Autowired
    private MemberStarLevelMapper memberStarLevelMapper;

    @Autowired
    private MemberStarInfoMapper memberStarInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public R upgradeStar(String userId) {

        QueryWrapper<MemberStarLevel> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<MemberStarLevel> memberStarLevels = memberStarLevelMapper.selectList(wrapper);
        MemberStarLevel memberStarLevel = memberStarLevels.get(0);
        MemberStarInfo memberStarInfo = baseMapper.findById(userId);
        log.info("会员id:" + userId + "点击升级一星达人");

        if(memberStarInfo ==null){
            MemberStarInfo membStarInfo =new MemberStarInfo();
            membStarInfo.setMemberId(Long.valueOf(userId));
            membStarInfo.setAddConsume(new BigDecimal("0"));
            membStarInfo.setConsumes(new BigDecimal("0"));
            membStarInfo.setCreationTime(new Date());
            baseMapper.insert(membStarInfo);
            memberStarInfo = baseMapper.findById(userId);
        }

        if (new BigDecimal(memberStarLevel.getOneCondition() + "").compareTo(memberStarInfo.getAddConsume()) == 1) {
            log.info("会员id:" + userId + "消费金额还没到达" + memberStarLevel.getOneCondition() + "元");
            return R.fail("消费金额还没到达" + memberStarLevel.getOneCondition() + "元");
        }

       /* MemberInfo memberInfo = memberInfoService.FindMemberInfo(userId);
        if(memberInfo.getStarId() == 1 ){
            return  R.fail("你已经升级为一星达人");
        }
        memberStarInfo.setStarId(1);
        memberStarInfo.setAddoneStar(0);
        memberStarInfo.setAddtwoStar(0);
        memberStarInfo.setAddthreeStar(0);
        memberStarInfo.setCreationTime(new Date());
        memberStarInfo.setOneStar(0);
        memberStarInfo.setTwoStar(0);
        memberStarInfo.setThreeStar(0);
        memberStarInfo.setStarOpen(1);
        memberStarInfo.setSubTwoStar(0);
        memberStarInfo.setSubThreeStar(0);
        memberStarInfo.setMemberAccount(memberInfo.getMemberAccount());
        QueryWrapper<MemberStarInfo> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("member_id",memberStarInfo.getMemberId());
        baseMapper.update(memberStarInfo,updateWrapper);
        memberInfo.setStarId(1);
        memberInfo.setStarOpen(1);

        memberInfoService.updateById(memberInfo);
        redisTemplate.opsForValue().set("member:info:" + memberInfo.getMemberId(), JSONArray.toJSONString(memberInfo));
        redisTemplate.opsForHash().put("member:star:info",userId, JSON.toJSONString(memberStarInfo));
        log.info("会员id:" + userId + "升级一星达人成功");
        MemberLeaders leadersName = memberLeadersService.findLeadersName(userId);
        int oneSubStar = 1;
        int twoSubStar = 0;
        int threeSubStar = 0;
        if (leadersName != null) {
            log.info("会员id:" + userId + "存在上级");
            upgradeStarIterator(1, memberStarLevels, leadersName, memberStarInfo, oneSubStar, twoSubStar, threeSubStar);
        }*/
        return R.ok(memberStarInfo);
    }



     /**
     *会员迭代升级
     * @param level 等级
     * @param memberStarLevels 会员升级规则
     * @param leadersName 上级
     * @param memberStarInfo 下级starinfo
     * @param oneSubStar 旗下一星
     * @param twoSubStar 旗下二星
     * @param threeSubStar 旗下三星
     * @return
     */
    public R upgradeStarIterator(int level, List<MemberStarLevel> memberStarLevels, MemberLeaders leadersName,
                                 MemberStarInfo memberStarInfo, int oneSubStar, int twoSubStar, int threeSubStar) {
        String aid =leadersName.getLeaderId();
        MemberStarInfo starInfo = memberStarInfoMapper.findById(leadersName.getLeaderId());
        MemberInfo memberInfo = null;
        if (starInfo != null && memberInfo !=null ) {
            if (memberStarInfo != null && memberStarInfo.getStarOpen() == 1) {
                if (starInfo.getStarOpen() == 1) {
                    switch (level) {
                        case 1:
                            starInfo.setAddoneStar(starInfo.getAddoneStar() + 1);
                            starInfo.setOneStar(starInfo.getOneStar() + 1);
                            starInfo.setSubOneStar(starInfo.getSubOneStar() + oneSubStar);
                            break;
                        case 2:
                            starInfo.setAddtwoStar(starInfo.getAddtwoStar() + 1);
                            starInfo.setSubTwoStar(starInfo.getSubTwoStar() + twoSubStar);
                            starInfo.setTwoStar(starInfo.getTwoStar() + 1);
                            break;
                        case 3:
                            starInfo.setAddthreeStar(starInfo.getAddthreeStar() + 1);
                            starInfo.setThreeStar(starInfo.getThreeStar() + 1);
                            starInfo.setSubThreeStar(starInfo.getSubThreeStar() + threeSubStar);
                            break;
                        default:
                            log.info("升级等级位创业达人");
                    }

                    QueryWrapper<MemberStarInfo> starInfoUpdate = new QueryWrapper<>();
                    starInfoUpdate.eq("member_id",starInfo.getMemberId());

                    if (starInfo.getStarId() == 1) {
                        MemberStarLevel memberStarLevel = memberStarLevels.get(1);
                        if (starInfo.getAddoneStar() >= Integer.valueOf(memberStarLevel.getOneCondition())) {
                            starInfo.setStarId(2);
                            level = 2;
                            int i = memberStarInfoMapper.update(starInfo,starInfoUpdate);
                            memberInfo.setStarId(2);
                            twoSubStar++;
                            log.info("会员id:" + aid + "升级二星达人成功");
                        }
                    }
                    if (starInfo.getStarId() == 2) {
                        MemberStarLevel memberStarLevel2 = memberStarLevels.get(2);
                        if (starInfo.getAddtwoStar() >= Integer.valueOf(memberStarLevel2.getOneCondition()) || starInfo.getSubOneStar() >= Integer.valueOf(memberStarLevel2.getTwoCondition())) {
                            starInfo.setStarId(3);
                            level = 3;
                            int i = memberStarInfoMapper.update(starInfo,starInfoUpdate);
                            memberInfo.setStarId(3);
                            threeSubStar++;
                            log.info("会员id:" + aid + "升级三星达人成功");
                        }
                    }
                    if (starInfo.getStarId() == 3) {
                        MemberStarLevel memberStarLevel3 = memberStarLevels.get(3);
                        if (starInfo.getAddthreeStar() >= Integer.valueOf(memberStarLevel3.getOneCondition()) || starInfo.getSubTwoStar() >= Integer.valueOf(memberStarLevel3.getTwoCondition())) {
                            starInfo.setStarId(4);
                            level = 4;
                            int i = memberStarInfoMapper.update(starInfo,starInfoUpdate);
                            memberInfo.setStarId(4);
                            log.info("会员id:" + aid + "升级四星达人成功");
                        }
                    }
                    memberInfoService.updateById(memberInfo);
                    redisTemplate.opsForValue().set("member:info:" + memberInfo.getMemberId(), JSONArray.toJSONString(memberInfo));
                    redisTemplate.opsForHash().put("member:star:info",starInfo.getMemberId(), JSON.toJSONString(starInfo));
                }
            } else {
                if (starInfo.getStarOpen() == 1) {
                    starInfo.setSubOneStar(starInfo.getOneStar() + oneSubStar);
                    starInfo.setSubTwoStar(starInfo.getSubTwoStar() + twoSubStar);
                    starInfo.setSubThreeStar(starInfo.getSubThreeStar() + threeSubStar);

                    QueryWrapper<MemberStarInfo> starInfoUpdate = new QueryWrapper<>();
                    starInfoUpdate.eq("member_id",starInfo.getMemberId());

                    if (starInfo.getStarId() == 2) {
                        MemberStarLevel memberStarLevel2 = memberStarLevels.get(2);
                        if (starInfo.getSubOneStar() >= Integer.valueOf(memberStarLevel2.getTwoCondition())) {
                            starInfo.setStarId(3);
                            level = 3;
                            int i = memberStarInfoMapper.update(starInfo,starInfoUpdate);
                            memberInfo.setStarId(3);
                            threeSubStar++;
                            log.info("会员id:" + aid + "升级三星达人成功");
                        }
                    }
                    if (starInfo.getStarId() == 3) {
                        MemberStarLevel memberStarLevel3 = memberStarLevels.get(3);
                        if (starInfo.getSubTwoStar() >= Integer.valueOf(memberStarLevel3.getTwoCondition())) {
                            starInfo.setStarId(4);
                            level = 4;
                            int i = memberStarInfoMapper.update(starInfo,starInfoUpdate);
                            memberInfo.setStarId(4);
                            log.info("会员id:" + aid + "升级四星达人成功");
                        }
                    }
                    memberInfoService.updateById(memberInfo);
                    redisTemplate.opsForValue().set("member:info:" + memberInfo.getMemberId(), JSONArray.toJSONString(memberInfo));
                    redisTemplate.opsForHash().put("member:star:info",starInfo.getMemberId(), JSON.toJSONString(starInfo));
                }
            }
        }
        MemberLeaders leadersNameNext = memberLeadersService.findLeadersName(leadersName.getLeaderId());
        if (leadersNameNext != null) {
            log.info("会员id:" + aid + "存在上级");
            upgradeStarIterator(level, memberStarLevels, leadersNameNext, starInfo, oneSubStar, twoSubStar, threeSubStar);
        }
        return R.ok();
    }
    @Transactional
    public R addConsume(String userId, BigDecimal consume) {
        MemberStarInfo memberStarInfo = memberStarInfoMapper.findById(userId);
        if (memberStarInfo == null) {
            MemberStarInfo membStarInfo =new MemberStarInfo();
            membStarInfo.setMemberId(Long.valueOf(userId));
            membStarInfo.setAddConsume(consume);
            membStarInfo.setConsumes(consume);
            membStarInfo.setCreationTime(new Date());
            baseMapper.insert(membStarInfo);
        } else {
            QueryWrapper<MemberStarInfo> wp = new QueryWrapper<>();
            wp.eq("member_id",userId);
            memberStarInfo.setAddConsume(memberStarInfo.getAddConsume().add(consume));
            memberStarInfo.setConsumes(memberStarInfo.getConsumes().add(consume));
            baseMapper.update(memberStarInfo,wp);
        }
        return R.ok();
    }

    @Override
    public R findConsume(String userId) {
        MemberStarInfo memberStarInfo = memberStarInfoMapper.findById(userId);
        if(memberStarInfo != null){
            BigDecimal addConsume = memberStarInfo.getAddConsume();
            return R.ok(addConsume.toString());
        }else {
            return R.ok("0");
        }

    }

    @Override
    public R upgradeStar2(String userId) {
        QueryWrapper<MemberStarLevel> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<MemberStarLevel> memberStarLevels = memberStarLevelMapper.selectList(wrapper);
        MemberStarLevel memberStarLevel = memberStarLevels.get(0);
        MemberStarInfo memberStarInfo = baseMapper.findById(userId);
        log.info("会员id:" + userId + "点击升级一星达人");
        if(memberStarInfo ==null){
            MemberStarInfo membStarInfo =new MemberStarInfo();
            membStarInfo.setMemberId(Long.valueOf(userId));
            membStarInfo.setAddConsume(new BigDecimal("0"));
            membStarInfo.setConsumes(new BigDecimal("0"));
            membStarInfo.setCreationTime(new Date());
            baseMapper.insert(membStarInfo);
            memberStarInfo = baseMapper.findById(userId);
        }
        MemberInfo memberInfo = null;
        memberStarInfo.setStarId(1);
        memberStarInfo.setAddoneStar(0);
        memberStarInfo.setAddtwoStar(0);
        memberStarInfo.setAddthreeStar(0);
        memberStarInfo.setCreationTime(new Date());
        memberStarInfo.setOneStar(0);
        memberStarInfo.setTwoStar(0);
        memberStarInfo.setThreeStar(0);
        memberStarInfo.setStarOpen(1);
        memberStarInfo.setSubTwoStar(0);
        memberStarInfo.setSubThreeStar(0);
        memberStarInfo.setMemberAccount(memberInfo.getMemberAccount());
        QueryWrapper<MemberStarInfo> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("member_id",memberStarInfo.getMemberId());
        baseMapper.update(memberStarInfo,updateWrapper);
        memberInfo.setStarId(1);
        memberInfoService.updateById(memberInfo);
        redisTemplate.opsForValue().set("member:info:" + memberInfo.getMemberId(), JSONArray.toJSONString(memberInfo));
        log.info("会员id:" + userId + "升级一星达人成功");
        return R.ok("升级成功");
    }


    @Override
    public R findStarInfo(String userId) {
        MemberStarInfo memberStarInfo = baseMapper.findById(userId);
        redisTemplate.opsForHash().put("member:star:info",userId, JSON.toJSONString(memberStarInfo));
        return R.ok(memberStarInfo);
    }

    @Override
    public BoostrapDataGrid starInfolist(MemberStarInfoObject qo) {
       int total = memberStarInfoMapper.starInfoTotal(qo);
       return new BoostrapDataGrid(total,total==0? Collections.EMPTY_LIST:memberStarInfoMapper.starInfolistRow(qo));
    }

}
