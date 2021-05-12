package com.zero2oneit.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.utils.R;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/2/24 15:56
 */
@Mapper
public interface GoodSaleAreaMapper extends BaseMapper<GoodSaleArea> {

    Integer selectTotal(GoodSaleAreaQueryObject qo);

    List<Map<String, Object>> selectAll(GoodSaleAreaQueryObject qo);

    List<GoodSaleAreaTree> tree();

    List<GoodSaleAreaTree> productPlaceTree();
}
