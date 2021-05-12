package com.zero2oneit.mall.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.bean.business.BusinessInfo;
import com.zero2oneit.mall.common.utils.suggest.Suggest;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-23
 */
public interface BusinessInfoService extends IService<BusinessInfo> {

    Suggest getBusinessByName(String keyword);

}

