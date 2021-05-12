package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.auth.WxAppletsAuth;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import com.zero2oneit.mall.common.utils.R;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/5 18:22
 */
public interface MemberLoginService extends IService<MemberInfo> {

    R WxAppletsLogin(WxAppletsAuth wxAppletsAuth);
}
