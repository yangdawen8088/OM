package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.SeckillRule;
import com.zero2oneit.mall.common.query.goods.SeckillRuleQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-03-17
 * @description
 */
@Mapper
public interface SeckillRuleMapper extends BaseMapper<SeckillRule> {

    int selectTotal(SeckillRuleQueryObject qo);

    List<Map<String, Object>> selectAll(SeckillRuleQueryObject qo);
}
