package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodsSpecs;
import com.zero2oneit.mall.common.query.goods.SpecsQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-05
 * @description
 */
@Mapper
public interface GoodsSpecsMapper extends BaseMapper<GoodsSpecs> {

    int selectTotal(SpecsQueryObject qo);

    List<Map<String, Object>> selectAll(SpecsQueryObject qo);

}
