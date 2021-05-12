package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.BargainRule;
import com.zero2oneit.mall.common.query.goods.BargainRuleQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tg
 * @create 2021-03-19
 * @description
 */
@Mapper
public interface BargainRuleMapper extends BaseMapper<BargainRule> {

    int selectTotal(BargainRuleQueryObject qo);

    List<HashMap<String,Object>> selectAll(BargainRuleQueryObject qo);

    void status(@Param("status") Integer status, @Param("id") String id);

}
