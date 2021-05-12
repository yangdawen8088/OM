package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.bean.member.LeaderVo;
import com.zero2oneit.mall.common.bean.member.MemberLeaders;
import com.zero2oneit.mall.common.query.member.memberLeadersObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
@Mapper
public interface MemberLeadersMapper extends BaseMapper<MemberLeaders> {

    int total(memberLeadersObject qo);

    List<HashMap<String,Object>> LeadersRows(memberLeadersObject qo);

    MemberLeaders findLeadersName(String memberId);

    List<LeaderVo> getAllLeader(String memberId);

}
