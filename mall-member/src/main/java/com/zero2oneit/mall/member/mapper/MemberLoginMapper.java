package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/5 18:23
 */
@Mapper
public interface MemberLoginMapper extends BaseMapper<MemberInfo> {
}
