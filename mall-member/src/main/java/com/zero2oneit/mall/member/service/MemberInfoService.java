package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.auth.AppWxLoginObject;
import com.zero2oneit.mall.common.bean.auth.RegisterInfo;
import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.bean.member.InfoSms;
import com.zero2oneit.mall.common.bean.member.MemberInfo;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.utils.WxInfo;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Sinper
 * @create 2020-07-15
 * @description
 */
public interface MemberInfoService extends IService<MemberInfo> {

    /**
     * 校验用户账号是否存在
     * @param phone
     * @return
     */
    boolean checkAccount(String phone);

    /**
     * 注册新用户
     * @param params
     */
    void register(Map<String, String> params);

    /**
     * 用户账号、密码登录
     * @param params
     * @return
     */
    R login(Map<String, String> params);

    /**
     * 忘记密码操作
     * @param params
     */
    void editOrForget(Map<String, String> params);

    /**
     * 设置支付密码
     * @param params
     */
    void setPayPwd(Map<String, String> params);

    /**
     * 修改个人信息
     * @param params
     */
    R edit(Map<String, String> params);

    /**
     * 微信一键登录
     * @param info
     * @return
     */
    R appletLogin(WxInfo info);

    /**
     * 判断用户原密码是否存在
     * @param memberAccount
     * @param password
     * @return
     */
    boolean check(String memberAccount, String password);

    /**
     * 编辑支付密码
     * @param params
     */
    void editPayPwd(Map<String, String> params);

    /**
     * 查询会员信息列表
     * @param qo
     * @return
     */
    BoostrapDataGrid pageList(InfoQueryObject qo);

    /**
     * 重置会员登录密码
     * @param memberId
     * @return
     */
    R resetPwd(String memberId);

}

