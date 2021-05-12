package com.zero2oneit.mall.business.controller;

import com.zero2oneit.mall.common.utils.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zero2oneit.mall.business.service.BusinessInfoService;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-23
 */
@RestController
@RequestMapping("/admin/communityBusiness")
public class BusinessInfoController {

    @Autowired
    private BusinessInfoService businessInfoService;

    /**
     * 根据商家名称匹配商家数据
     * @param keyword
     * @return
     */
    @RequestMapping("/getBusinessByName")
    public Suggest getBusinessByName(@RequestBody String keyword) {//VO : value-object 值对象
        return businessInfoService.getBusinessByName(keyword);
    }

}
