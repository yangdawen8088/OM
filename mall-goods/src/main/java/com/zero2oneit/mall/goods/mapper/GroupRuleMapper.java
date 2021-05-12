package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GroupRule;
import com.zero2oneit.mall.common.query.goods.GroupRuleQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-03-19
 * @description
 */
@Mapper
public interface GroupRuleMapper extends BaseMapper<GroupRule> {

    int selectTotal(GroupRuleQueryObject qo);

    List<Map<String, Object>> selectAll(GroupRuleQueryObject qo);


}
