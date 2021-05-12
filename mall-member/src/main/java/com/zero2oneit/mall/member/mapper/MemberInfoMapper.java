package com.zero2oneit.mall.member.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import com.zero2oneit.mall.member.utils.WxInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sinper
 * @create 2020-07-15
 * @description
 */
@Mapper
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {

    Integer checkAccount(String phone);

    void register(@Param("params") Map<String, String> params);

    void editOrForget(@Param("params") Map<String, String> params);

    void setPayPwd(@Param("params") Map<String, String> params);

    void edit(@Param("params") Map<String, String> params);

    void addUser(WxInfo info);

    void editUser(WxInfo info);

    int check(@Param("memberAccount") String memberAccount, @Param("md5Password") String md5Password);

    int selectTotal(InfoQueryObject qo);

    List<HashMap<String,Object>> selectRows(InfoQueryObject qo);

    void resetPwd(@Param("md5Password") String md5Password, @Param("memberId") String memberId);

    void updateStaIdByMemberId(@Param("memberId") String memberId, @Param("id") String id);

}
