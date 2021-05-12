package com.zero2oneit.mall.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.market.Advert;
import com.zero2oneit.mall.common.query.market.AdvertQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-01-25
 * @description
 */
@Mapper
public interface AdvertMapper extends BaseMapper<Advert> {

    int selectTotal(AdvertQueryObject qo);

    List<Map<String, Object>> selectAll(AdvertQueryObject qo);

    void add(Advert advert);

}
