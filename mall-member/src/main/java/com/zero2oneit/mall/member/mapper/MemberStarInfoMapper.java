package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.bean.member.MemberStarInfo;
import com.zero2oneit.mall.common.query.member.MemberStarInfoObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhj
 * @create 2021-01-27
 * @description
 */
@Mapper
public interface MemberStarInfoMapper extends BaseMapper<MemberStarInfo> {


    MemberStarInfo findById(@Param("userId") String userId);

    int starInfoTotal(MemberStarInfoObject qo);

    List<HashMap<String,Object>> starInfolistRow(MemberStarInfoObject qo);
}
