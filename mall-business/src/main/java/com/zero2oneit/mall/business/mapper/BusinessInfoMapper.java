package com.zero2oneit.mall.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero2oneit.mall.common.bean.business.BusinessInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tg
 * @create 2021-02-23
 * @description
 */
@Mapper
public interface BusinessInfoMapper extends BaseMapper<BusinessInfo> {

    List<HashMap<String,Object>> getBusinessByName(String keyword);

}
