package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.bean.member.MemberFans;
import com.zero2oneit.mall.common.query.member.MemberFansObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
@Mapper
public interface MemberFansMapper extends BaseMapper<MemberFans> {


    int total(MemberFansObject qo);

    int totalGrade(MemberFansObject qo);

    List<HashMap<String,Object>> fansRows(MemberFansObject qo);

    List<HashMap<String,Object>> memberFansRows(MemberFansObject qo);

    List<HashMap<String,Object>> fansRowsGrade(MemberFansObject qo);
}
