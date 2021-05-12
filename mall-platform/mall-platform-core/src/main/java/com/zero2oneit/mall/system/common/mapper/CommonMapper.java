package com.zero2oneit.mall.system.common.mapper;

import com.zero2oneit.mall.common.utils.select2.Select2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonMapper {

    List<Select2> select2(@Param("codeTable") String codeTable, @Param("codeField") String codeField);

}
