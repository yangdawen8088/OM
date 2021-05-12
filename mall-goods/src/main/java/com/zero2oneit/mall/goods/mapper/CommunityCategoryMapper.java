package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.CommunityCategory;
import com.zero2oneit.mall.common.query.goods.CommunityCategoryQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-05
 * @description
 */
@Mapper
public interface CommunityCategoryMapper extends BaseMapper<CommunityCategory> {

    int selectTotal(CommunityCategoryQueryObject qo);

    List<Map<String, Object>> selectAll(CommunityCategoryQueryObject qo);

    List<HashMap<String,Object>> getCategoryByName(String keyword);

}
