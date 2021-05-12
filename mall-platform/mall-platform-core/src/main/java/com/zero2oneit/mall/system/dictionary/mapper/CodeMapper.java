package com.zero2oneit.mall.system.dictionary.mapper;

import com.zero2oneit.mall.system.dictionary.query.CodeQueryObject;
import com.zero2oneit.mall.system.dictionary.vo.CodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CodeMapper {

    int selectTotal(CodeQueryObject qo);

    List<HashMap<String,Object>> selectRows(CodeQueryObject qo);

    void add(CodeVO codeVO);

    void edit(CodeVO codeVO);

    void changeCodeStatus(@Param("status") String status, @Param("ids") String ids);

}
