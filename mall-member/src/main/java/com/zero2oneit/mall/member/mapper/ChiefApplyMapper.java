package com.zero2oneit.mall.member.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Tg
 * @create 2021-04-27
 * @description
 */
@Mapper
public interface ChiefApplyMapper extends BaseMapper<ChiefApply> {

    List<Map<String, Object>>  selectAll(ChiefManageQueryObject qo);

    int selectTotal(ChiefManageQueryObject qo);

    void updateStaIdById(String id);

}
