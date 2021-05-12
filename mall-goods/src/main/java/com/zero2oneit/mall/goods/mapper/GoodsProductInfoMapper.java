package com.zero2oneit.mall.goods.mapper;

import com.zero2oneit.mall.common.bean.goods.GoodsProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.Products;
import com.zero2oneit.mall.common.query.goods.GoodsProductInfoQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-02-22
 * @description
 */
@Mapper
public interface GoodsProductInfoMapper extends BaseMapper<GoodsProductInfo> {

    int selectTotal(GoodsProductInfoQueryObject qo);

    List<Map<String, Object>> selectAll(GoodsProductInfoQueryObject qo);

    void status(@Param("id") String id, @Param("status") Integer status);

    void audit(@Param("ids") String ids, @Param("productStatus") Integer productStatus, @Param("approveResult") String approveResult, @Param("userName") String userName);

    Map<String, Object> load(String id);

    void copyGoods(@Param("nextId") long nextId, @Param("id") String id);

    void copyGoodsRelation(@Param("nextId") long nextId, @Param("id") String id);

    List<Products> esList(String ids);

    void bindSecKill(@Param("productIds") String productIds, @Param("ruleId") String ruleId);

    void unSeckill(String productIds);

}
