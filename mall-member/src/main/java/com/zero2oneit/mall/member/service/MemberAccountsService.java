package com.zero2oneit.mall.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberAccounts;

/**
 * @author Atzel
 * @create 2020-07-22
 * @description
 */
public interface MemberAccountsService extends IService<MemberAccounts> {

    /**
     * 手机号码注册添加用户账户信息
     * @param memberId
     */
    void add(long memberId);

}

