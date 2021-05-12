package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodsSpu;
import com.zero2oneit.mall.common.query.goods.GoodsSpuQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-03-08
 * @description
 */
@Mapper
public interface GoodsSpuMapper extends BaseMapper<GoodsSpu> {

    Map<String, Object> report();

    int selectTotal(GoodsSpuQueryObject qo);

    List<Map<String, Object>> selectAll(GoodsSpuQueryObject qo);

    void status(@Param("id") String id, @Param("status") Integer status);

    void audit(@Param("ids") String ids, @Param("productStatus") Integer productStatus, @Param("approveResult") String approveResult, @Param("userName") String userName);

    Map<String, Object> load(String id);

    List<Map<String, Object>> loadSku(String id);

}
