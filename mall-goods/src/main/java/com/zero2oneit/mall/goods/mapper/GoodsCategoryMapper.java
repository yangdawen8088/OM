package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodsCategory;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.utils.R;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-06
 * @description
 */
@Mapper
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    int selectTotal(GoodsCategoryQueryObject qo);

    List<Map<String, Object>> selectAll(GoodsCategoryQueryObject qo);

    List<GoodsCategory> categoryList();

    void status(@Param("id") String id, @Param("status") Integer status);

    List<HashMap<String,Object>> getCategoryByName(String keyword);
}
