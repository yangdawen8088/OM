package com.zero2oneit.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.member.MemberAddress;
import com.zero2oneit.mall.common.query.member.MemberAddressQueryObject;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-04-21
 */
public interface MemberAddressService extends IService<MemberAddress> {

    /**
     * 前端查询会员地址列表
     * @param qo
     * @return
     */
    BoostrapDataGrid pageList(MemberAddressQueryObject qo);

    /**
     * 设置默认地址
     * @param id
     */
    void setDefault(Long id, Long memberId);

}

