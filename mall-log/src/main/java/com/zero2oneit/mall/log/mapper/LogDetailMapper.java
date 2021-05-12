package com.zero2oneit.mall.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.log.LogDetail;
import com.zero2oneit.mall.common.query.log.LogDetailQueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tg
 * @create 2021-03-24
 * @description
 */
@Mapper
public interface LogDetailMapper extends BaseMapper<LogDetail> {

    int selectTotal(LogDetailQueryObject qo);

    List<HashMap<String,Object>> selectRows(LogDetailQueryObject qo);

}
