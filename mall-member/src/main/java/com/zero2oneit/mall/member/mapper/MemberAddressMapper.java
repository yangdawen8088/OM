package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.member.MemberAddress;
import com.zero2oneit.mall.common.query.member.MemberAddressQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-04-21
 * @description
 */
@Mapper
public interface MemberAddressMapper extends BaseMapper<MemberAddress> {

    int selectTotal(MemberAddressQueryObject qo);

    List<Map<String, Object>> selectAll(MemberAddressQueryObject qo);

    void setDefault(@Param("id") Long id, @Param("memberId") Long memberId);

}
