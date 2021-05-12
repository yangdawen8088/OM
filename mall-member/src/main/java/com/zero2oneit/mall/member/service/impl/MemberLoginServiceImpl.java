package com.zero2oneit.mall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero2oneit.mall.common.bean.auth.WxAppletsAuth;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import com.zero2oneit.mall.common.utils.BeanUtils;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.member.mapper.MemberLoginMapper;
import com.zero2oneit.mall.member.service.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/5 18:22
 */
@Service
public class MemberLoginServiceImpl extends ServiceImpl<MemberLoginMapper, MemberInfo> implements MemberLoginService {

    @Autowired
    private MemberLoginMapper memberLoginMapper;

    @Override
    public R WxAppletsLogin(WxAppletsAuth wxAppletsAuth) {
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyBeanProp(memberInfo,wxAppletsAuth);
        memberInfo.setMemberAccount(memberInfo.getMemberPhone());
        MemberInfo queryMember = getOne(new QueryWrapper<MemberInfo>().eq("member_phone", wxAppletsAuth.getMemberPhone()));
        if (ObjectUtils.isEmpty(queryMember)){
            save(memberInfo);
            return R.ok();
        }
        memberInfo.setMemberId(queryMember.getMemberId());
        updateById(memberInfo);
        return R.ok();
    }

}
