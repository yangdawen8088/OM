package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.bean.member.MemberStarLevel;
import com.zero2oneit.mall.common.query.member.MemberStarLevelObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhj
 * @create 2021-01-27
 * @description
 */
@Mapper
public interface MemberStarLevelMapper extends BaseMapper<MemberStarLevel> {

    void addManageAccount(@Param("id") Integer id, @Param("wallet") BigDecimal wallet);

    int starlevelTotal(MemberStarLevelObject qo);

    List<HashMap<String,Object>> starlevelRow(MemberStarLevelObject qo);


}
