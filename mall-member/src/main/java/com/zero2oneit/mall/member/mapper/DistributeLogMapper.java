package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.member.DistributeLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Description:
 *
 * @author leon
 * @date 2020/12/29 15:17
 */
@Mapper
public interface DistributeLogMapper extends BaseMapper<DistributeLog> {

    BigDecimal getTotalPlatformJf();

    Map<String, BigDecimal>  GetAllBalance(@Param("oneState") Integer oneState, @Param("twoState") Integer twoState, @Param("businessId") String businessId);


}
