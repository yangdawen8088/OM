package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.IntegralRule;
import com.zero2oneit.mall.common.query.goods.IntegralRuleQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-05
 * @description
 */
@Mapper
public interface IntegralRuleMapper extends BaseMapper<IntegralRule> {

    int selectTotal(IntegralRuleQueryObject qo);

    List<Map<String, Object>> selectAll(IntegralRuleQueryObject qo);

}
