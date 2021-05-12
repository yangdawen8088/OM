package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodsBrand;
import com.zero2oneit.mall.common.query.goods.GoodsBrandQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-19
 * @description
 */
@Mapper
public interface GoodsBrandMapper extends BaseMapper<GoodsBrand> {

    int selectTotal(GoodsBrandQueryObject qo);

    List<Map<String, Object>> selectAll(GoodsBrandQueryObject qo);

    void status(@Param("id") String id, @Param("status") Integer status);

    List<HashMap<String,Object>> getGoodsBrandByName(String keyword);

}
