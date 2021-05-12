package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.member.MemberAccounts;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
@Mapper
public interface MemberAccountsMapper extends BaseMapper<MemberAccounts> {

   void add(long memberId);

}
