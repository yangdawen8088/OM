package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.CommunityCategories;
import com.zero2oneit.mall.common.query.goods.CommunityCategoriesQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-05
 * @description
 */
@Mapper
public interface CommunityCategoriesMapper extends BaseMapper<CommunityCategories> {

    int selectTotal(CommunityCategoriesQueryObject qo);

    List<Map<String, Object>> selectAll(CommunityCategoriesQueryObject qo);

    List<CommunityCategories> categoryList();

    void status(@Param("id") String id, @Param("status") Integer status);

    List<HashMap<String,Object>> getCategoryByName(String keyword);

    List<CommunityCategories> categorys();

}
