package com.zero2oneit.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 10:53
 */
@Mapper
public interface StationManageMapper extends BaseMapper<StationManage> {

    Integer selectTotal(StationManageQueryObject qo);

    List<Map<String, Object>> selectAll(StationManageQueryObject qo);

    List<BaseTreeNode> tree();
}
